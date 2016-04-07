package com.artivisi.aplikasipembayaran.service;

import com.artivisi.aplikasipembayaran.dao.GcmOutgoingMessageDao;
import com.artivisi.aplikasipembayaran.entity.GcmMessageStatus;
import com.artivisi.aplikasipembayaran.entity.GcmOutgoingMessage;
import com.artivisi.aplikasipembayaran.entity.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

@Service
@Transactional
public class GcmService {

    private static final Logger LOG = LoggerFactory.getLogger(GcmService.class);
    private static final ObjectMapper jsonMapper = new ObjectMapper();
    private static final RestTemplate gcmServer = new RestTemplate();
    private static final String GCM_SERVER_URL = "https://gcm-http.googleapis.com/gcm/send";
    private static final String GCM_API_KEY = "AIzaSyCOyIvrPeTlssOCcPNNict4eKSZ0DPF6s8";

    @Autowired private GcmOutgoingMessageDao gcmOutgoingMessageDao;


    public void kirimUpdateProduk(){

    }

    public void kirimUpdateTagihan(User u){

    }

    public void kirimKeGcm(){
        LOG.debug("GCM : Starting GCM Sender");

        PageRequest page = new PageRequest(0,1);
        Page<GcmOutgoingMessage> msgs
                = gcmOutgoingMessageDao
                .findByStatusOrderByTerakhirUpdateDesc(GcmMessageStatus.NEW, page);


        LOG.debug("Found {} outgoing GCM message", msgs.getTotalElements());

        if(!msgs.hasContent()){
            return;
        }

        GcmOutgoingMessage msg = msgs.getContent().get(0);

        try {
            Map<String, Object> mapMsg = gcmObjectToMap(msg);
            sendToGCMServer(mapMsg, msg);
            gcmOutgoingMessageDao.save(msg);
            LOG.debug("GCM : Sent message [{}] successfully", msg.getId());
        } catch (Exception err){
            LOG.warn("Error processing GCM Outgoing [{}]", msg.getId());
            msg.setStatus(GcmMessageStatus.FAILED);
            LOG.debug("GCM : Fail to send message [{}]", msg.getId());
        }

        LOG.debug("GCM : Finish processing queue");
    }

    private void sendToGCMServer(Map<String, Object> msg, GcmOutgoingMessage gcmMsg){
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON_UTF8);
        header.add("Authorization", "key=" + GCM_API_KEY);

        HttpEntity<Map> httpEntity = new HttpEntity<>(msg, header);

        LOG.debug("GCM : Sending to GCM Server");

        ResponseEntity<Map> response = gcmServer.exchange(GCM_SERVER_URL,
                HttpMethod.POST,
                httpEntity,
                Map.class);

        Map<String, Object> responseData = response.getBody();

        List<Map<String, Object>> results = (List<Map<String, Object>>) responseData.get("results");
        LOG.debug("GCM : Receive {} results", results.size());

        if(results !=  null && !results.isEmpty()){
            Map<String, Object> msgResp = results.get(0);
            String gcmId = (String) msgResp.get("message_id");
            if(gcmId != null && !gcmId.isEmpty()) {
                gcmMsg.setGcmId(gcmId);
            }

            String error = (String) msgResp.get("error");
            if(error != null && !error.isEmpty()){
                gcmMsg.setStatus(GcmMessageStatus.FAILED);
                gcmMsg.setFailedMessage(error);
            } else {
                gcmMsg.setStatus(GcmMessageStatus.SENT);
            }
        }
    }

    private Map<String, Object> gcmObjectToMap(GcmOutgoingMessage msg) throws Exception {
        Map<String, Object> gcmMsg = new HashMap<>();
        gcmMsg.put("to", msg.getTo());

        if (msg.getData() != null) {
            Map<String, Object> data = readStringToMap(msg.getData());
            if(data != null) {
                gcmMsg.put("data", data);
            }
        }

        if (msg.getNotification() != null) {
            Map<String, Object> notif = readStringToMap(msg.getNotification());
            if(notif != null) {
                gcmMsg.put("notification", notif);
            }
        }

        if (msg.getCollapseKey() != null) {
            gcmMsg.put("collapse_key", msg.getCollapseKey());
        }

        if (msg.getTimeToLive() != null) {
            gcmMsg.put("time_to_live", msg.getTimeToLive());
        }
        return gcmMsg;
    }

    private Map<String, Object> readStringToMap(String content) throws Exception{
        Map<String, Object> data
            = jsonMapper.readValue(content,
            new TypeReference<Map<String, Object>>() {
            });
        return data;
    }
}

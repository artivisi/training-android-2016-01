package com.artivisi.android.aplikasipembayaran.restclient;

import com.artivisi.android.aplikasipembayaran.dto.GenericResponse;

import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

/**
 * Created by endymuhardin on 4/6/16.
 */
public class PembayaranRestClient {

    private static final String SERVER_URL = "http://192.168.100.3:8080";
    private RestTemplate restTemplate = new RestTemplate();


    public GenericResponse login(String username, String password){
        String url = SERVER_URL + "/api/login";

        HashMap<String, String> requestData = new HashMap<>();
        requestData.put("username", username);
        requestData.put("password", password);

        return restTemplate.postForObject(url, requestData, GenericResponse.class);
    }

}

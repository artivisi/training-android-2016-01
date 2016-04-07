package com.artivisi.aplikasipembayaran.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity @Table(name = "t_gcm_outgoing_message")
public class GcmOutgoingMessage {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @NotNull @NotEmpty
    @Column(name="message_to", nullable = false)
    private String to;
    private String notification;
    private String data;

    @Column(name = "collapse_key")
    private String collapseKey;
    @Column(name = "ttl")
    private Integer timeToLive;
    @Column(name = "gcm_id")
    private String gcmId;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GcmMessageStatus status = GcmMessageStatus.NEW;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCollapseKey() {
        return collapseKey;
    }

    public void setCollapseKey(String collapseKey) {
        this.collapseKey = collapseKey;
    }

    public Integer getTimeToLive() {
        return timeToLive;
    }

    public void setTimeToLive(Integer timeToLive) {
        this.timeToLive = timeToLive;
    }

    public String getGcmId() {
        return gcmId;
    }

    public void setGcmId(String gcmId) {
        this.gcmId = gcmId;
    }

    public GcmMessageStatus getStatus() {
        return status;
    }

    public void setStatus(GcmMessageStatus status) {
        this.status = status;
    }
}

package com.artivisi.aplikasipembayaran.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by endymuhardin on 4/7/16.
 */
@Entity
@Table(name = "m_handphone")
public class Handphone extends BaseEntity {

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    @NotNull @NotEmpty @Size(min = 10)
    @Column(name = "gcm_token", nullable = false)
    private String gcmToken;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getGcmToken() {
        return gcmToken;
    }

    public void setGcmToken(String gcmToken) {
        this.gcmToken = gcmToken;
    }
}

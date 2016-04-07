package com.artivisi.aplikasipembayaran.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "m_user")
public class User extends BaseEntity {

    @NotNull @NotEmpty
    @Column(nullable = false, unique = true)
    private String username;

    @NotNull @NotEmpty
    @Column(nullable = false)
    private String fullname;

    @NotNull @NotEmpty @Email
    @Column(nullable = false, unique = true)
    private String email;

    @NotNull @NotEmpty
    @Column(nullable = false, name = "nomer_handphone")
    private String nomerHandphone;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNomerHandphone() {
        return nomerHandphone;
    }

    public void setNomerHandphone(String nomerHandphone) {
        this.nomerHandphone = nomerHandphone;
    }
}

package com.artivisi.aplikasipembayaran.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "m_user_password")
public class UserPassword extends BaseEntity {

    @NotNull
    @OneToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    @NotNull @NotEmpty
    @Size(min = 4)
    @Column(name = "hashed_password", nullable = false)
    private String password;
}

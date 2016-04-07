package com.artivisi.aplikasipembayaran.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by endymuhardin on 4/7/16.
 */
@Entity
@Table(name = "m_produk")
public class Produk extends BaseEntity {

    @NotNull
    @Size(min = 3)
    @Column(nullable = false, unique = true)
    private String kode;

    @NotNull
    @Size(min = 3)
    @Column(nullable = false)
    private String nama;
}

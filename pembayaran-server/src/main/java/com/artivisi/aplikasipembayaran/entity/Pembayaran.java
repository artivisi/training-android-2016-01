package com.artivisi.aplikasipembayaran.entity;

import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

/**
 * Created by endymuhardin on 4/7/16.
 */
@Entity
@Table(name = "t_pembayaran")
public class Pembayaran extends BaseEntity {

    @NotNull
    @OneToOne
    @JoinColumn(name = "id_tagihan", nullable = false)
    private Tagihan tagihan;

    @NotNull @Past
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "waktu_transaksi", nullable = false)
    private Date waktuTransaksi;

    @NotNull @Min(0)
    @Column(nullable = false)
    private BigDecimal nilai = BigDecimal.ZERO;
}

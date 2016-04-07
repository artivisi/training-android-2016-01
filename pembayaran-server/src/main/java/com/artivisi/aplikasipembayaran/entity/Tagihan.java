package com.artivisi.aplikasipembayaran.entity;

import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

@Entity
@Table(name = "t_tagihan")
public class Tagihan extends BaseEntity {

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_produk", nullable = false)
    private Produk produk;

    @NotNull
    @Past
    @Temporal(TemporalType.DATE)
    @Column(name = "periode_tagihan", nullable = false)
    private Date periodeTagihan;

    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name = "jatuh_tempo", nullable = false)
    private Date jatuhTempo;

    @NotNull
    @Min(0)
    @Column(nullable = false)
    private BigDecimal tagihan = BigDecimal.ZERO;

    @NotNull
    @Min(0)
    @Column(nullable = false)
    private BigDecimal denda = BigDecimal.ZERO;

    @NotNull
    @Min(0)
    @Column(nullable = false)
    private BigDecimal admin = BigDecimal.ZERO;

    @NotNull
    @Min(0)
    @Column(nullable = false)

    private BigDecimal pajak = BigDecimal.ZERO;

    public BigDecimal getPajak() {
        return pajak;
    }

    public void setPajak(BigDecimal pajak) {
        this.pajak = pajak;
    }

    public BigDecimal getAdmin() {
        return admin;
    }

    public void setAdmin(BigDecimal admin) {
        this.admin = admin;
    }

    public BigDecimal getDenda() {
        return denda;
    }

    public void setDenda(BigDecimal denda) {
        this.denda = denda;
    }

    public BigDecimal getTagihan() {
        return tagihan;
    }

    public void setTagihan(BigDecimal tagihan) {
        this.tagihan = tagihan;
    }

    public Date getJatuhTempo() {
        return jatuhTempo;
    }

    public void setJatuhTempo(Date jatuhTempo) {
        this.jatuhTempo = jatuhTempo;
    }

    public Date getPeriodeTagihan() {
        return periodeTagihan;
    }

    public void setPeriodeTagihan(Date periodeTagihan) {
        this.periodeTagihan = periodeTagihan;
    }

    public Produk getProduk() {
        return produk;
    }

    public void setProduk(Produk produk) {
        this.produk = produk;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

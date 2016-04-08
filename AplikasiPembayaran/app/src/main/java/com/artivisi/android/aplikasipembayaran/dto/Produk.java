package com.artivisi.android.aplikasipembayaran.dto;

import java.util.Date;

/**
 * Created by jimmy on 08/04/16.
 */
public class Produk {

    private String id;
    private String kode;
    private String nama;
    private Long terakhirUpdate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Long getTerakhirUpdate() {
        return terakhirUpdate;
    }

    public void setTerakhirUpdate(Long terakhirUpdate) {
        this.terakhirUpdate = terakhirUpdate;
    }
}

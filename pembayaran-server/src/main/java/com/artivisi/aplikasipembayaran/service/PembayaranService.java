package com.artivisi.aplikasipembayaran.service;

import com.artivisi.aplikasipembayaran.entity.Produk;
import com.artivisi.aplikasipembayaran.entity.Tagihan;
import com.artivisi.aplikasipembayaran.entity.User;

import java.util.Date;
import java.util.List;

public class PembayaranService {
    public void createTagihan(Produk p, User u, Tagihan t){
        // insert ke database

        // send notif ke handset user
    }

    public void saveProduk(Produk p){
        // insert / update ke database

        // send notif ke semua handset
    }

    public List<Tagihan> cariTagihan(User u){
        return null;
    }

    public List<Produk> cariProduk(Date terakhirUpdate){
        return null;
    }
}

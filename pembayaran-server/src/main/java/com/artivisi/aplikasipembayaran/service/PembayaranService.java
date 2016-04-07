package com.artivisi.aplikasipembayaran.service;

import com.artivisi.aplikasipembayaran.dao.HandphoneDao;
import com.artivisi.aplikasipembayaran.dao.ProdukDao;
import com.artivisi.aplikasipembayaran.dao.TagihanDao;
import com.artivisi.aplikasipembayaran.dao.UserDao;
import com.artivisi.aplikasipembayaran.entity.Handphone;
import com.artivisi.aplikasipembayaran.entity.Produk;
import com.artivisi.aplikasipembayaran.entity.Tagihan;
import com.artivisi.aplikasipembayaran.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class PembayaranService {

    private static final ObjectMapper jsonMapper = new ObjectMapper();
    private static final Logger LOG = LoggerFactory.getLogger(PembayaranService.class);

    @Autowired private TagihanDao tagihanDao;
    @Autowired private HandphoneDao handphoneDao;
    @Autowired private UserDao userDao;
    @Autowired private ProdukDao produkDao;

    @Autowired private GcmService gcmService;

    public void createTagihan(Tagihan t){
        tagihanDao.save(t);
        gcmService.kirimUpdateTagihan(t.getUser());
    }

    public void saveProduk(Produk p){
        produkDao.save(p);
        gcmService.kirimUpdateProduk();
    }

    public List<Tagihan> cariTagihan(User u, Date terakhirUpdate){
        return null;
    }

    public List<Produk> cariProduk(Date terakhirUpdate){
        return null;
    }

    public void hapusHandphone(Handphone h){
        handphoneDao.delete(h);
    }

    public void updateHandphoneUser(User u, String gcmToken){
        if(u != null){
            Handphone h = new Handphone();
            h.setUser(u);
            h.setGcmToken(gcmToken);
            handphoneDao.save(h);
            gcmService.registrasiHandphone(h);
        }
    }

    public Page<Produk> semuaProduk(Pageable page) {
        return produkDao.findAll(page);
    }
}

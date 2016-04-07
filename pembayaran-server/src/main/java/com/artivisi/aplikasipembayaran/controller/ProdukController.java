package com.artivisi.aplikasipembayaran.controller;

import com.artivisi.aplikasipembayaran.entity.Produk;
import com.artivisi.aplikasipembayaran.service.PembayaranService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/produk")
public class ProdukController {

    @Autowired private PembayaranService pembayaranService;

    @RequestMapping(value="/", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid Produk p){
        pembayaranService.saveProduk(p);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Page<Produk> semuaProduk(Pageable page){
        return pembayaranService.semuaProduk(page);
    }
}

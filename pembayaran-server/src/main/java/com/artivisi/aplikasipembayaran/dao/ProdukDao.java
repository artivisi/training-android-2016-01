package com.artivisi.aplikasipembayaran.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.artivisi.aplikasipembayaran.entity.*;

public interface ProdukDao extends PagingAndSortingRepository<Produk, String> {
}

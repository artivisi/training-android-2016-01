package com.artivisi.aplikasipembayaran.dao;

import com.artivisi.aplikasipembayaran.entity.Handphone;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by endymuhardin on 4/7/16.
 */
public interface HandphoneDao extends PagingAndSortingRepository<Handphone, String> {
}

package com.artivisi.aplikasipembayaran.dao;

import com.artivisi.aplikasipembayaran.entity.Handphone;
import com.artivisi.aplikasipembayaran.entity.User;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by endymuhardin on 4/7/16.
 */
public interface HandphoneDao extends PagingAndSortingRepository<Handphone, String> {
    List<Handphone> findByUser(User u);
}

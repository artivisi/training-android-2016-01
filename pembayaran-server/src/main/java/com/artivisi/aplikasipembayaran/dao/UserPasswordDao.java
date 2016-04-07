package com.artivisi.aplikasipembayaran.dao;

import com.artivisi.aplikasipembayaran.entity.UserPassword;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by endymuhardin on 4/7/16.
 */
public interface UserPasswordDao extends PagingAndSortingRepository<UserPassword, String> {
}

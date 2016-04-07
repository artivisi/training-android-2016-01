package com.artivisi.aplikasipembayaran.dao;

import com.artivisi.aplikasipembayaran.entity.GcmOutgoingMessage;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by endymuhardin on 4/7/16.
 */
public interface GcmOutgoingMessageDao extends PagingAndSortingRepository<GcmOutgoingMessage, String> {
}

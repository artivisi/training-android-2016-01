package com.artivisi.aplikasipembayaran.dao;

import com.artivisi.aplikasipembayaran.entity.GcmMessageStatus;
import com.artivisi.aplikasipembayaran.entity.GcmOutgoingMessage;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by endymuhardin on 4/7/16.
 */
public interface GcmOutgoingMessageDao extends PagingAndSortingRepository<GcmOutgoingMessage, String> {

    public Page<GcmOutgoingMessage> findByStatusOrderByTerakhirUpdate(GcmMessageStatus status, Pageable page);

}

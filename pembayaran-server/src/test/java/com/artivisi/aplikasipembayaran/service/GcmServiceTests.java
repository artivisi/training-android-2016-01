package com.artivisi.aplikasipembayaran.service;


import com.artivisi.aplikasipembayaran.PembayaranServerApplication;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PembayaranServerApplication.class)
@Sql(scripts = "/samples/gcm-outgoing.sql")
public class GcmServiceTests {

    @Autowired private GcmService gcmService;

    @Test
    public void testSendGcmMessage(){
        gcmService.kirimKeGcm();
    }
}

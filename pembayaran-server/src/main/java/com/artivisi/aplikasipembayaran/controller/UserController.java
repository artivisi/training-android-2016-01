package com.artivisi.aplikasipembayaran.controller;

import com.artivisi.aplikasipembayaran.dao.UserDao;
import com.artivisi.aplikasipembayaran.entity.User;
import com.artivisi.aplikasipembayaran.service.PembayaranService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    @Autowired private PembayaranService pembayaranService;
    @Autowired private UserDao userDao;

    @RequestMapping(value = "/{username}/handphone", method = RequestMethod.PUT)
    public void updateHandphoneUser(@PathVariable String username,
                                    @RequestBody Map<String, String> data) {

        User u = userDao.findByUsername(username);
        if(u == null){
            return;
        }

        String token = data.get("gcm_token");
        if(token != null && !token.isEmpty()) {
            pembayaranService.updateHandphoneUser(u, token);
        }
    }
}

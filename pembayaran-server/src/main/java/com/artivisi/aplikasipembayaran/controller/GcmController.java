package com.artivisi.aplikasipembayaran.controller;

import org.springframework.web.bind.annotation.*;

import com.artivisi.aplikasipembayaran.dto.GenericResponse;

@RestController
public class GcmController {
	
	@RequestMapping(value="/api/gcm", method=RequestMethod.POST)
	public GenericResponse registrasiGcmToken(
						@RequestParam String username,
						@RequestParam String token){
		System.out.println("Token : "+token);
		GenericResponse hasil = new GenericResponse();
		hasil.setSuccess(true);
		hasil.getData().put("username", username);
		hasil.getData().put("token", token);
		return hasil;
	}
}
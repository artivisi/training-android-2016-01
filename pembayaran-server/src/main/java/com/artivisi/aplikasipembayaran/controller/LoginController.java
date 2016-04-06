package com.artivisi.aplikasipembayaran.controller;

import java.util.*;

import org.springframework.web.bind.annotation.*;

import com.artivisi.aplikasipembayaran.dto.GenericResponse;

@RestController
public class LoginController {

	@RequestMapping(value="/api/login", 
					method=RequestMethod.POST)
	public GenericResponse login(
					@RequestBody Map<String, String> data){
		String userValid = "endy";
		String passValid = "123";

		GenericResponse response = new GenericResponse();

		if(userValid.equals(data.get("username")) 
			    && passValid.equals(data.get("password"))){
			response.setSuccess(true);

			Map<String, Object> content = new HashMap<>();
			content.put("username", "endy");
			content.put("fullname", "Endy Muhardin");
			content.put("email", "endy@muhardin.com");

			response.setData(content);
		} else {
			response.setSuccess(false);

			Map<String, Object> content = new HashMap<>();
			content.put("errorcode", "401");
			content.put("errormessage", "Username atau Password salah");
			response.setData(content);
		}
		return response;
	}

}
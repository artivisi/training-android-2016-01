package com.artivisi.aplikasipembayaran.dto;

import java.util.Map;
import java.util.HashMap;

public class GenericResponse {
	private Boolean success;
	private Map<String, Object> data;

	public Boolean isSuccess(){
		return success;
	}

	public void setSuccess(Boolean x){
		this.success = x;
	}

	public Map<String, Object> getData(){
		return data;
	}

	public void setData(Map<String, Object> x){
		this.data = x;
	}
}
package com.artivisi.android.aplikasipembayaran.dto;

import java.util.Map;

/**
 * Created by endymuhardin on 4/6/16.
 */
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

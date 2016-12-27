package com.ctjsoft.xh.changancentre.model;

import java.util.HashMap;

/**
 * Created by Tim on 2016/12/23.
 */
public class HttpMessage {

    public String message;

    public HashMap<String,String> errors;


    public HttpMessage(String message) {
        this.message = message;
    }

    public void putError(String key, String value){
        if(errors==null){
            errors = new HashMap<>();
        }

        if(key!=null && value!=null){
            errors.put(key,value);
        }
        else{
            throw new RuntimeException("putError参数传入的值为null！");
        }
        return;
    }
}

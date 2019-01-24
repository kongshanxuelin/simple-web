package com.sumslack.web.simple.exception;

import java.io.Serializable;

public class BusinessException extends RuntimeException implements Serializable {

    public BusinessException(){
        super();
    }

    public BusinessException(String msg){
        super(msg);
    }

    public BusinessException(Exception e){
        super(e);
    }
}

package com.sumslack.web.simple.exception;

import java.io.Serializable;

/**
 * 授权
 *
 */
public class AuthorizationException extends RuntimeException implements Serializable {

    public AuthorizationException(){
        super();
    }

    public AuthorizationException(String msg){
        super(msg);
    }

    public AuthorizationException(Exception e){
        super(e);
    }
}

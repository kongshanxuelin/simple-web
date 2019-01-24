package com.sumslack.web.simple.exception;

import java.io.Serializable;

public class LoginFailedException extends RuntimeException implements Serializable{

    public LoginFailedException(){
        super();
    }

    public LoginFailedException(String msg){
        super(msg);
    }

    public LoginFailedException(Exception e){
        super(e);
    }
}

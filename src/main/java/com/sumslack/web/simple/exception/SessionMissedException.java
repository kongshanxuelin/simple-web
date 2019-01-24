package com.sumslack.web.simple.exception;

import java.io.Serializable;

public class SessionMissedException extends RuntimeException implements Serializable {

    public SessionMissedException(){
        super();
    }

    public SessionMissedException(String msg){
        super(msg);
    }

    public SessionMissedException(Exception e){
        super(e);
    }
}

package com.sumslack.web.simple.exception;

import java.io.Serializable;

public class PageSessionMissedException extends Exception implements Serializable {

    public PageSessionMissedException(){
        super();
    }

    public PageSessionMissedException(String msg){
        super(msg);
    }

    public PageSessionMissedException(Exception e){
        super(e);
    }
}

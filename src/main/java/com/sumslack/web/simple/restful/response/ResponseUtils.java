package com.sumslack.web.simple.restful.response;

import javax.servlet.http.HttpServletRequest;

public class ResponseUtils {

    public static <T> ResponseData<T> ok(T data){
        return new ResponseData(new MetaData(ErrorNum.SUCCESS.value(), ErrorNum.SUCCESS.description(), null, null), data);
    }

    public static <T> ResponseData<T> error(ErrorNum errnNum, String errMsg, HttpServletRequest request){
        return new ResponseData(new MetaData(errnNum.value(), errMsg, request.getMethod(), request.getRequestURI()), VoidData.VOID);
    }

}

package com.sumslack.web.simple.restful.response;

import java.io.Serializable;

public class MetaData implements Serializable{

    private int errNum;
    private String errMsg;
    private String requestMethod;
    private String requestURI;

    public MetaData(int errNum, String errMsg, String requestMethod, String requestURI) {
        this.errNum = errNum;
        this.errMsg = errMsg;
        this.requestMethod = requestMethod;
        this.requestURI = requestURI;
    }

    public int getErrNum() {
        return errNum;
    }

    public void setErrNum(int errNum) {
        this.errNum = errNum;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getRequestURI() {
        return requestURI;
    }

    public void setRequestURI(String requestURI) {
        this.requestURI = requestURI;
    }
}

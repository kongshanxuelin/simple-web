package com.sumslack.web.simple.restful.response;

import java.io.Serializable;

public class ResponseData<T> implements Serializable {
    private MetaData meta;
    private T data;

    public ResponseData(MetaData meta, T data) {
        this.meta = meta;
        this.data = data;
    }

    public MetaData getMeta() {
        return meta;
    }

    public void setMeta(MetaData meta) {
        this.meta = meta;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

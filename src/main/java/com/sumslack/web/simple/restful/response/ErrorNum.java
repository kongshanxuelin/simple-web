package com.sumslack.web.simple.restful.response;

public final class ErrorNum {
    public final static ErrorNum SUCCESS = new ErrorNum(0, "无错误");
    public static final ErrorNum DATABASE_ERROR = new ErrorNum(200, "数据库异常");
    public static final ErrorNum FAILED_LOGIN = new ErrorNum(300, "登录失败");
    public static final ErrorNum INVALID_ARGUMENT = new ErrorNum(400, "参数错误");
    public static final ErrorNum SESSION_MISS = new ErrorNum(500, "会话失效");
    public static final ErrorNum BUSINESS_ERROR = new ErrorNum(600, "业务异常");
    public static final ErrorNum AUTH_ERROR = new ErrorNum(600, "未授权");

    private int errNum;
    private String description;

    private ErrorNum(int errNum, String description) {
        this.errNum = errNum;
        this.description = description;
    }

    public int value() {
        return this.errNum;
    }

    public String description() {
        return this.description;
    }
}

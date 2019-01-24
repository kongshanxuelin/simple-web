package com.sumslack.web.simple.exception.handler;

import com.sumslack.web.simple.exception.*;
import com.sumslack.web.simple.restful.response.ErrorNum;
import com.sumslack.web.simple.restful.response.ResponseData;
import com.sumslack.web.simple.restful.response.ResponseUtils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(LoginFailedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public final static ResponseData handleLoginFailedException(LoginFailedException exception, HttpServletRequest request) {
        return ResponseUtils.error(ErrorNum.SUCCESS, exception.getMessage(), request);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public final static ResponseData handleIllegalArgumentException(IllegalArgumentException exception, HttpServletRequest request) {
        return ResponseUtils.error(ErrorNum.INVALID_ARGUMENT, exception.getMessage(), request);
    }

    @ExceptionHandler(SQLException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public final static ResponseData handleSQLException(SQLException exception, HttpServletRequest request) {
        logger.error("Database operation error found", exception);
        return ResponseUtils.error(ErrorNum.DATABASE_ERROR, "Database operate error", request);
    }

    @ExceptionHandler(PageSessionMissedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public final static String handlePageSessionMissedException(PageSessionMissedException exception, HttpServletRequest request, HttpServletResponse response) {
        response.addHeader("sessionStatus", "timeout");
        return "login";
    }

    @ExceptionHandler(SessionMissedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public final static Object handleSessionMissedException(SessionMissedException exception, HttpServletRequest request, HttpServletResponse response) {
        response.addHeader("sessionStatus", "timeout");
        return ResponseUtils.error(ErrorNum.SESSION_MISS, ErrorNum.SESSION_MISS.description(), request);
    }

    @ExceptionHandler(AuthorizationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public final static Object handleAuthorizationException(AuthorizationException exception, HttpServletRequest request, HttpServletResponse response) {
        logger.error(exception.getMessage());
        return ResponseUtils.error(ErrorNum.AUTH_ERROR, ErrorNum.AUTH_ERROR.description(), request);
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public final static ResponseData handleBusinessException(BusinessException exception, HttpServletRequest request) {
        logger.error(exception.getMessage());
        return ResponseUtils.error(ErrorNum.BUSINESS_ERROR, exception.getMessage(), request);
    }


}

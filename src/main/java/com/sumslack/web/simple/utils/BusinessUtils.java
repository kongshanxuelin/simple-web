package com.sumslack.web.simple.utils;

import com.sumslack.web.simple.constant.*;
import com.sumslack.web.simple.dto.LoginInfoDTO;
import com.sumslack.web.simple.exception.AuthorizationException;
import com.sumslack.web.simple.exception.SessionMissedException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class BusinessUtils {

    public static void checkSession(HttpServletRequest request){
        HttpSession session = request.getSession();
        LoginInfoDTO loginInfo = (LoginInfoDTO) session.getAttribute(Constant.LOGIN_SESSION_KEY);
        if (loginInfo == null) {
            throw new SessionMissedException("Please login.");
        }
    }

    public static void checkAdminSession(HttpServletRequest request){
        HttpSession session = request.getSession();
        LoginInfoDTO loginInfo = (LoginInfoDTO) session.getAttribute(Constant.LOGIN_SESSION_KEY);
        if (loginInfo == null) {
            throw new SessionMissedException("Please login.");
        }
        else{
            if(!loginInfo.getRoles().contains(Role.FINANCE.getValue()) && !loginInfo.getRoles().contains(Role.HR.getValue())){
                throw new AuthorizationException(String.format("the user[%s] is not authorize", loginInfo.getUser().getUsername()));
            }
        }
    }

    public static final boolean checkTeamMemberRole(String role){
        return Role.CUSTOMER_MGR.getValue().equals(role) || Role.TEAM_MGR.getValue().equals(role);
    }


}

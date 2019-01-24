package com.sumslack.web.simple.restful;

import com.sumslack.web.simple.constant.Constant;
import com.sumslack.web.simple.constant.Role;
import com.sumslack.web.simple.dto.LoginInfoDTO;
import com.sumslack.web.simple.exception.LoginFailedException;
import com.sumslack.web.simple.model.TeamWithRoleTypeModel;
import com.sumslack.web.simple.model.UserModel;
import com.sumslack.web.simple.restful.response.ResponseUtils;
import com.sumslack.web.simple.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/authentication", produces = {MediaType.APPLICATION_JSON_VALUE})
public class AuthenticationController {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Object login(String username, String password, HttpServletRequest request) throws LoginFailedException {
        UserModel userModel = userService.login(username, password);
        Set<String> roles = userService.queryRoles(userModel.getUserId());
        if(!roles.contains(Role.HR.getValue()) && !roles.contains(Role.FINANCE.getValue())){
            roles.add(Role.CUSTOMER_MGR.getValue());
        }
        List<TeamWithRoleTypeModel> teamList =  userService.queryTeam(userModel.getUserId());
        LoginInfoDTO loginInfo = new LoginInfoDTO();
        loginInfo.setUser(userModel);
        loginInfo.setRoles(roles);
        loginInfo.setBelongTeamIds(teamList.stream().map(team -> team.getTeamId()).collect(Collectors.toSet()));
        loginInfo.setOwnerTeamIds(teamList.stream().filter(team -> team.getType().equals(Role.TEAM_MGR.getValue())).map(team -> team.getTeamId()).collect(Collectors.toSet()));
        HttpSession session = request.getSession(true);
        session.setAttribute(Constant.LOGIN_SESSION_KEY, loginInfo);
        return ResponseUtils.ok(null);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public Object logout(HttpServletRequest request){
        HttpSession session = request.getSession(true);
        session.removeAttribute(Constant.LOGIN_SESSION_KEY);
        session.invalidate();
        return ResponseUtils.ok(null);
    }
}

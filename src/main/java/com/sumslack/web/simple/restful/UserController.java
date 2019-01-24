package com.sumslack.web.simple.restful;

import com.sumslack.web.simple.constant.Constant;
import com.sumslack.web.simple.dto.*;
import com.sumslack.web.simple.model.UserModel;
import com.sumslack.web.simple.model.UserWithRoleTypeModel;
import com.sumslack.web.simple.restful.response.ResponseData;
import com.sumslack.web.simple.restful.response.ResponseUtils;
import com.sumslack.web.simple.service.UserService;
import com.sumslack.web.simple.utils.BusinessUtils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/user", produces = {MediaType.APPLICATION_JSON_VALUE})
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/changePassword", method = RequestMethod.POST)
    public ResponseData changePassword(String oldPassword, String newPassword, HttpServletRequest request){
        BusinessUtils.checkSession(request);
        LoginInfoDTO loginInfo = (LoginInfoDTO) request.getSession(true).getAttribute(Constant.LOGIN_SESSION_KEY);
        userService.changePassword(loginInfo.getUser().getUserId(), oldPassword, newPassword);
        return ResponseUtils.ok(null);
    }

    @RequestMapping(value = "/queryTeamById", method = RequestMethod.POST)
    public Object queryTeamById(String teamId, String draw, HttpServletRequest request){
        BusinessUtils.checkSession(request);
        List<UserWithRoleTypeModel> userList =  userService.queryByTeamId(teamId);
        List<UserWithRoleDTO> userDtoList = new ArrayList<>(userList.size());
        for(UserWithRoleTypeModel userWithRoleTypeModel : userList){
            UserWithRoleDTO userWithRoleDTO = new UserWithRoleDTO();
            BeanUtils.copyProperties(userWithRoleTypeModel, userWithRoleDTO);
            userDtoList.add(userWithRoleDTO);
        }
        Map<String, Object> ret = new HashMap<>();
        ret.put("recordsTotal", userDtoList.size());
        ret.put("recordsFiltered", userDtoList.size());
        ret.put("data", userDtoList);
        ret.put("draw", draw);
        return ret;
    }
    
    @RequestMapping(value = "/user-list", method = RequestMethod.GET)
    public List userList(HttpServletRequest request){
        return userService.selectUserChoose();
    }

    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public Object query(String keyword, int start, int length, String draw, HttpServletRequest request){
        BusinessUtils.checkSession(request);
        List<UserModel> userList = userService.query(keyword, start, length);
        List<UserDTO> userDtoList = new ArrayList<>(userList.size());
        for(UserModel user : userList){
            UserDTO userDTO = new UserDTO();
            BeanUtils.copyProperties(user, userDTO);
            userDtoList.add(userDTO);
        }
        long count = userService.queryCount(keyword);
        Map<String, Object> ret = new HashMap<>();
        ret.put("recordsTotal", count);
        ret.put("recordsFiltered", count);
        ret.put("data", userDtoList);
        ret.put("draw", draw);
        return ret;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Object create(@RequestBody UserCreateDTO userCreateDTO, HttpServletRequest request){
        BusinessUtils.checkAdminSession(request);
        LoginInfoDTO loginInfo = (LoginInfoDTO) request.getSession(true).getAttribute(Constant.LOGIN_SESSION_KEY);
        userService.addUser(userCreateDTO, loginInfo.getUser().getUserId());
        return ResponseUtils.ok(null);
    }
    
    @RequestMapping(value = "/create-batch", method = RequestMethod.POST)
    public Object createBatch(@RequestBody List<String> userCreateDTOList, HttpServletRequest request){
        BusinessUtils.checkAdminSession(request);
        LoginInfoDTO loginInfo = (LoginInfoDTO) request.getSession(true).getAttribute(Constant.LOGIN_SESSION_KEY);
        int result = userService.addUsers(userCreateDTOList, loginInfo.getUser().getUserId());
        return ResponseUtils.ok(result);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Object update(@RequestBody UserUpdateDTO userUpdateDTO, HttpServletRequest request){
        BusinessUtils.checkAdminSession(request);
        LoginInfoDTO loginInfo = (LoginInfoDTO) request.getSession(true).getAttribute(Constant.LOGIN_SESSION_KEY);
        userService.updateUser(userUpdateDTO, loginInfo.getUser().getUserId());
        return ResponseUtils.ok(null);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Object delete(String userId, HttpServletRequest request){
        BusinessUtils.checkAdminSession(request);
        LoginInfoDTO loginInfo = (LoginInfoDTO) request.getSession(true).getAttribute(Constant.LOGIN_SESSION_KEY);
        userService.delUser(userId, loginInfo.getUser().getUserId());
        return ResponseUtils.ok(null);
    }
}

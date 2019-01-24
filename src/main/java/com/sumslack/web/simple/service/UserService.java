package com.sumslack.web.simple.service;

import com.sumslack.web.simple.constant.UserStatus;
import com.sumslack.web.simple.dao.RoleUserModelMapper;
import com.sumslack.web.simple.dao.TeamModelMapper;
import com.sumslack.web.simple.dao.UserModelMapper;
import com.sumslack.web.simple.dto.UserCreateDTO;
import com.sumslack.web.simple.dto.UserQueryDTO;
import com.sumslack.web.simple.dto.UserUpdateDTO;
import com.sumslack.web.simple.exception.BusinessException;
import com.sumslack.web.simple.exception.LoginFailedException;
import com.sumslack.web.simple.model.RoleUserModel;
import com.sumslack.web.simple.model.TeamWithRoleTypeModel;
import com.sumslack.web.simple.model.UserModel;
import com.sumslack.web.simple.model.UserWithRoleTypeModel;
import com.sumslack.web.simple.utils.Utils;

import cn.hutool.core.util.StrUtil;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserModelMapper userModelMapper;

    @Autowired
    private RoleUserModelMapper roleUserModelMapper;

    @Autowired
    private TeamModelMapper teamModelMapper;


    @Transactional
    public int addUsers(List<String> userCreateDTOes,String operatorUserId) {
    	int result = 0;
    	for(String userCreateDTO : userCreateDTOes) {
    		if(!StrUtil.isBlankIfStr(userCreateDTO)) {
    			if(userModelMapper.selectByUsername(userCreateDTO) == null){  //不存在这个用户才添加
    				UserModel userModel = new UserModel();
    				userModel.setUsername(userCreateDTO);
    		        userModel.setPassword(Utils.md5("123456"));
    		        userModel.setUserId(Utils.uuid());
    		        userModel.setStatus(UserStatus.ENABLE);
    		        userModel.setRealname(userCreateDTO);
    		        userModel.setAge(26);
    		        userModel.setSex("m");
    		        userModel.setStatus("0");
    		        userModel.setEmail(userCreateDTO + "@batch.com");
    		        userModel.setCreateTime(new Date());
    		        userModel.setUpdateTime(new Date());
    		        if(userModelMapper.insert(userModel)>0) {
    		        	++result;
    		        }
    			}
    		}
    	}
    	return result;
    }
    @Transactional
    public void addUser(UserCreateDTO userCreateDTO, String operatorUserId){
        Assert.notNull(userCreateDTO, "userUpdateDTO is null");
        Assert.isTrue(StringUtils.isNotBlank(userCreateDTO.getRealname()), "realname is blank.");
        Assert.isTrue(StringUtils.isNotBlank(userCreateDTO.getUsername()), "username is blank.");
        Assert.isTrue(StringUtils.isNotBlank(userCreateDTO.getSex()), "sex is blank.");
        Assert.isTrue(StringUtils.isNotBlank(userCreateDTO.getEmail()), "email is blank.");
        Assert.isTrue(userCreateDTO.getAge() != null && userCreateDTO.getAge() > 18, "age is illegal,must greater then 18 old.");
        Assert.isTrue(StringUtils.isNotBlank(operatorUserId), "operatorUserId is blank.");
        if(userModelMapper.selectByUsername(userCreateDTO.getUsername()) != null){
            logger.warn(String.format("The username[%s] is already registered."));
            throw new BusinessException("该用户名已经被注册");
        }
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userCreateDTO, userModel);
        userModel.setPassword(Utils.md5(userCreateDTO.getPassword()));
        userModel.setUserId(Utils.uuid());
        userModel.setStatus(UserStatus.ENABLE);
        userModel.setCreateTime(new Date());
        userModel.setUpdateTime(new Date());
        userModelMapper.insert(userModel);
    }

    @Transactional
    public void updateUser(UserUpdateDTO userUpdateDTO, String operatorUserId){
        Assert.notNull(userUpdateDTO, "userUpdateDTO is null");
        Assert.isTrue(StringUtils.isNotBlank(userUpdateDTO.getUserId()), "userId is blank.");
        Assert.isTrue(StringUtils.isNotBlank(userUpdateDTO.getRealname()), "realname is blank.");
        Assert.isTrue(StringUtils.isNotBlank(userUpdateDTO.getUsername()), "username is blank.");
        Assert.isTrue(StringUtils.isNotBlank(userUpdateDTO.getSex()), "sex is blank.");
        Assert.isTrue(StringUtils.isNotBlank(userUpdateDTO.getEmail()), "email is blank.");
        Assert.isTrue(userUpdateDTO.getAge() != null && userUpdateDTO.getAge() >= 18, "age is illegal.");
        Assert.isTrue(StringUtils.isNotBlank(operatorUserId), "operatorUserId is blank.");
        UserModel userModel = userModelMapper.selectByUserId(userUpdateDTO.getUserId());
        if(userModel == null){
            logger.warn(String.format("Can not find user by userId[%s].", userUpdateDTO.getUserId()));
            throw new BusinessException("找不到该用户");
        }
        UserModel userModel1ByUsername = userModelMapper.selectByUsername(userUpdateDTO.getUsername());
        if(userModel1ByUsername != null
                && !userModel1ByUsername.getUserId().equals(userUpdateDTO.getUserId())
                && userModel1ByUsername.getUsername().equals(userUpdateDTO.getUsername())){
            throw new BusinessException("该用户名已经被使用");
        }

        // 如果密码为空，则不修改密码；否则，就修改密码
        if(StringUtils.isBlank(userUpdateDTO.getPassword())){
            userUpdateDTO.setPassword(userModel.getPassword());
        }
        else{
            userUpdateDTO.setPassword(Utils.md5(userUpdateDTO.getPassword()));
        }
        BeanUtils.copyProperties(userUpdateDTO, userModel);
        userModel.setUpdateTime(new Date());
        userModelMapper.updateByUserId(userModel);
    }

    @Transactional
    public void delUser(String toDelUserId, String operatorUserId){
        Assert.isTrue(StringUtils.isNotBlank(toDelUserId), "toDelUserId is blank.");
        Assert.isTrue(StringUtils.isNotBlank(operatorUserId), "operatorUserId is blank.");
        UserModel userModel = userModelMapper.selectByUserId(toDelUserId);
        if(userModel == null){
            logger.warn(String.format("Can not find user by userId[%s].", toDelUserId));
            throw new BusinessException("找不到该用户");
        }
        List<TeamWithRoleTypeModel> teamList = teamModelMapper.selectByUserId(toDelUserId);
        if(!CollectionUtils.isEmpty(teamList)){
            throw new BusinessException("该用户属于某个团队，不能删除");
        }
        if(UserStatus.DISABLE.equals(userModel.getStatus())){
            throw new BusinessException("该用户已经删除");
        }
        userModel.setStatus(UserStatus.DISABLE);
        userModel.setUpdateTime(new Date());
        userModelMapper.updateByUserId(userModel);
    }

    public List selectUserChoose() {
    	return userModelMapper.selectUserChoose();
    }

    public UserModel getByUserId(String userId){
        //Assert.notNull(userId, "userId is null,"+Utils.formatNull(userId));
        return userModelMapper.selectByUserId(userId);
    }

    public List<UserWithRoleTypeModel> queryByTeamId(String teamId){
        Assert.notNull(teamId, "teamId is null");
        return userModelMapper.selectByTeamId(teamId);
    }

    public List<UserModel> queryByUserIdList(List<String> userIdList){
        if(CollectionUtils.isEmpty(userIdList)){
            return Collections.EMPTY_LIST;
        }
        return userModelMapper.selectByUserIdList(userIdList);
    }

    public UserModel login(String username, String password) {
        Assert.notNull(username, "username is null.");
        Assert.notNull(password, "password is null.");
        UserModel userModel = userModelMapper.selectByUsername(username);
        if(userModel == null){
            logger.error(String.format("Fail to login because can not find user[%s].", username));
            throw new LoginFailedException(String.format("用户名或密码错误", username));
        }
        if(!Utils.md5(password).equals(userModel.getPassword())){
            logger.error(String.format("Fail to login because wrong password[%s].", username));
            throw new LoginFailedException(String.format("用户名或密码错误", username));
        }
        return userModel;
    }

    public Set<String> queryRoles(String userId){
        Assert.notNull(userId, "userId is null.");
        List<RoleUserModel> list = roleUserModelMapper.selectByUserId(userId);
        if(CollectionUtils.isEmpty(list)){
            return new HashSet<>();
        }
        return list.stream().map(roleUser -> roleUser.getRoleName()).collect(Collectors.toSet());
    }

    public List<TeamWithRoleTypeModel> queryTeam(String userId){
        List<TeamWithRoleTypeModel> list = teamModelMapper.selectByUserId(userId);
        if(list == null){
            return Collections.EMPTY_LIST;
        }
        return list;
    }

    public UserModel getTeamManagerByTeamId(String teamId){
        Assert.notNull(teamId, "teamId is null");
        return userModelMapper.getTeamManagerByTeamId(teamId);
    }

    public void changePassword(String userId, String oldPassword, String newPassword){
        Assert.notNull(userId, "userId is null");
        Assert.notNull(oldPassword, "oldPassword is null");
        Assert.notNull(newPassword, "newPassword is null");
        UserModel userModel = userModelMapper.selectByUserId(userId);
        if(userModel == null){
            logger.error(String.format("Can not find user by userId when change password.userId:%s, oldPassword:%s, newPassword:%s", userId, oldPassword, newPassword));
            throw  new BusinessException(String.format("can find user by user id", userId));
        }
        if(oldPassword.equals(newPassword)){
            logger.error(String.format("The old password is same with new password when change password.userId:%s, oldPassword:%s, newPassword:%s", userId, oldPassword, newPassword));
            throw  new BusinessException("原始密码和新密码不能一样");
        }
        if(!Utils.md5(oldPassword).equals(userModel.getPassword())){
            logger.error(String.format("The old password is wrong when change password.userId:%s, oldPassword:%s, newPassword:%s", userId, oldPassword, newPassword));
            throw  new BusinessException("原始密码错误");
        }
        if(newPassword.length() < 6){
            logger.error(String.format("The length of new password is less than 6 when change password.userId:%s, oldPassword:%s, newPassword:%s", userId, oldPassword, newPassword));
            throw  new BusinessException("新密码至少要6位");
        }
        userModel.setPassword(Utils.md5(newPassword));
        userModel.setUpdateTime(new Date());
        userModelMapper.updateByUserId(userModel);
    }

    public List<UserModel> query(String keyword, int start, int limit){
        if(start< 0){
            start = 0;
        }
        if(limit < 1){
            limit = 10;
        }
        UserQueryDTO queryDTO = new UserQueryDTO();
        queryDTO.setKeyword(keyword);
        queryDTO.setStart(start);
        queryDTO.setLimit(limit);
        return userModelMapper.select(queryDTO);
    }

    public Long queryCount(String keyword){
        UserQueryDTO queryDTO = new UserQueryDTO();
        queryDTO.setKeyword(keyword);
        return userModelMapper.selectCount(queryDTO);
    }
}


package com.sumslack.web.simple.dao;

import com.sumslack.web.simple.dto.UserQueryDTO;
import com.sumslack.web.simple.model.UserModel;
import com.sumslack.web.simple.model.UserWithRoleTypeModel;

import org.apache.catalina.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserModelMapper {

    int insert(UserModel record);

    UserModel selectByUsername(String username);

    UserModel selectByUserId(String userId);

    List<UserModel> selectByUserIdList(@Param("userIdList") List<String> userIdList);

    UserModel getTeamManagerByTeamId(String teamId);

    int updateByUserId(UserModel record);

    List<UserWithRoleTypeModel> selectByTeamId(String teamId);


    List<UserModel> select(UserQueryDTO userQueryDTO);

    long selectCount(UserQueryDTO userQueryDTO);
    
    List selectUserChoose();
}
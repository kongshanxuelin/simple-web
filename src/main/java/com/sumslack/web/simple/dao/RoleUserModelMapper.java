package com.sumslack.web.simple.dao;

import org.apache.ibatis.annotations.Mapper;

import com.sumslack.web.simple.model.RoleUserModel;

import java.util.List;

@Mapper
public interface RoleUserModelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RoleUserModel record);

    int insertSelective(RoleUserModel record);

    RoleUserModel selectByPrimaryKey(Long id);

    List<RoleUserModel> selectByUserId(String userId);

    int updateByPrimaryKeySelective(RoleUserModel record);

    int updateByPrimaryKey(RoleUserModel record);
}
package com.sumslack.web.simple.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sumslack.web.simple.model.TeamUserModel;

@Mapper
public interface TeamUserModelMapper {
    int deleteByTeamIdUserId(@Param("teamId") String teamId, @Param("userId")String userId);

    int insert(TeamUserModel record);

    TeamUserModel selectByTeamIdUserId(@Param("teamId") String teamId, @Param("userId")String userId);

    int updateByPrimaryKey(TeamUserModel record);
    String selectTeamNameByUserId(String userId);
}
package com.sumslack.web.simple.dao;

import org.apache.ibatis.annotations.Mapper;

import com.sumslack.web.simple.model.TeamModel;
import com.sumslack.web.simple.model.TeamWithRoleTypeModel;

import java.util.List;

@Mapper
public interface TeamModelMapper {
    int insert(TeamModel record);
    List<TeamModel> queryAll();
    TeamModel selectByTeamId(String teamId);
    List<TeamModel> selectByName(String name);
    List<TeamWithRoleTypeModel> selectByUserId(String userId);
    void deleteByTeamId(String teamId);
}
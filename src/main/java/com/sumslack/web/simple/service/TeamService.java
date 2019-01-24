package com.sumslack.web.simple.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import com.sumslack.web.simple.constant.Role;
import com.sumslack.web.simple.constant.UserStatus;
import com.sumslack.web.simple.dao.RoleUserModelMapper;
import com.sumslack.web.simple.dao.TeamModelMapper;
import com.sumslack.web.simple.dao.TeamUserModelMapper;
import com.sumslack.web.simple.dao.UserModelMapper;
import com.sumslack.web.simple.exception.BusinessException;
import com.sumslack.web.simple.model.RoleUserModel;
import com.sumslack.web.simple.model.TeamModel;
import com.sumslack.web.simple.model.TeamUserModel;
import com.sumslack.web.simple.model.TeamWithRoleTypeModel;
import com.sumslack.web.simple.model.UserModel;
import com.sumslack.web.simple.model.UserWithRoleTypeModel;
import com.sumslack.web.simple.utils.BusinessUtils;
import com.sumslack.web.simple.utils.Utils;

@Service
public class TeamService {

    private static final Logger logger = LoggerFactory.getLogger(TeamService.class);

    @Autowired
    private TeamModelMapper teamModelMapper;
    @Autowired
    private UserModelMapper userModelMapper;
    @Autowired
    private TeamUserModelMapper teamUserModelMapper;
    @Autowired
    private RoleUserModelMapper roleUserModelMapper;

    @Transactional
    public void createTeam(String name, String description){
        Assert.notNull(name, "name is null");
        Assert.notNull(description, "description is null");
        List<TeamModel> teamModelList = teamModelMapper.selectByName(name);
        if(!CollectionUtils.isEmpty(teamModelList)){
            logger.warn(String.format("The team[%s] is already exists. ", name));
            throw new BusinessException("该组已经存在");
        }
        TeamModel teamModel = new TeamModel();
        teamModel.setTeamId(Utils.uuid());
        teamModel.setName(name);
        teamModel.setDescription(description);
        teamModel.setCreateTime(new Date());
        teamModel.setUpdateTime(new Date());
        teamModelMapper.insert(teamModel);
    }

    @Transactional
    public void deleteTeam(String teamId){
        Assert.notNull(teamId, "teamId is null");
        List<UserWithRoleTypeModel> userWithRoleTypeModelList = userModelMapper.selectByTeamId(teamId);
        if(!CollectionUtils.isEmpty(userWithRoleTypeModelList)){
            throw new BusinessException("团队还有成员，不能删除");
        }
        teamModelMapper.deleteByTeamId(teamId);
    }

    public List<TeamModel> queryAllTeam(){
        return teamModelMapper.queryAll();
    }
    
    public TeamModel getTeam(String teamId) {
    	return teamModelMapper.selectByTeamId(teamId);
    }

    @Transactional
    public void removeTeamMember(String teamId, String userId){
        Assert.notNull(teamId, "teamId is null");
        Assert.notNull(userId, "userId is null");
        teamUserModelMapper.deleteByTeamIdUserId(teamId, userId);
    }

    @Transactional
    public void updateTeamMemberRole(String teamId, String userId, String roleType){
        Assert.notNull(teamId, "teamId is null");
        Assert.notNull(userId, "userId is null");
        Assert.isTrue(BusinessUtils.checkTeamMemberRole(roleType), String.format("team member roleType[%s] is illegal.", roleType));
        List<RoleUserModel> roleUserModelList = roleUserModelMapper.selectByUserId(userId);
        if(Role.TEAM_MGR.getValue().equals(roleType)){
            UserModel teamManager = userModelMapper.getTeamManagerByTeamId(teamId);
            if(teamManager != null){
                throw new BusinessException("该团队已经有团队经理");
            }
            // 设置“团队经理”角色
            if(!roleUserModelList.stream().anyMatch(roleUserModel -> roleUserModel.getRoleName().equals(Role.TEAM_MGR.getValue()))){
                RoleUserModel roleUserModel = new RoleUserModel();
                roleUserModel.setUserId(userId);
                roleUserModel.setRoleName(Role.TEAM_MGR.getValue());
                roleUserModel.setCreateTime(new Date());
                roleUserModel.setUpdateTime(new Date());
                roleUserModelMapper.insert(roleUserModel);
            }
        }
        else{
            // 取消“团队经理”角色
            List<RoleUserModel>  filteredList = roleUserModelList.stream().filter(roleUserModel -> roleUserModel.getRoleName().equals(Role.TEAM_MGR.getValue())).collect(Collectors.toList());
            for(RoleUserModel ele : filteredList){
                roleUserModelMapper.deleteByPrimaryKey(ele.getId());
            }
        }
        TeamUserModel teamUserModel = teamUserModelMapper.selectByTeamIdUserId(teamId, userId);
        teamUserModel.setType(roleType);
        teamUserModel.setUpdateTime(new Date());
        teamUserModelMapper.updateByPrimaryKey(teamUserModel);
    }

    @Transactional
    public void joinTeam(String teamId, String userId){
        Assert.notNull(teamId, "teamId is null");
        Assert.notNull(userId, "userId is null");
        if(teamModelMapper.selectByTeamId(teamId) == null){
            throw new BusinessException("该团队不存在");
        }
        UserModel userModel = userModelMapper.selectByUserId(userId);
        if(userModel == null ){
            throw new BusinessException("该用户不存在");
        }
        if(!UserStatus.ENABLE.equals(userModel.getStatus())){
            throw new BusinessException("该用户已删除");
        }
        List<TeamWithRoleTypeModel> teamWithRoleTypeList = teamModelMapper.selectByUserId(userId);
        if(!CollectionUtils.isEmpty(teamWithRoleTypeList)){
            throw new BusinessException("该用户是其他团队的成员，不加入到新团队");
        }
        List<RoleUserModel> roleUserModelList = roleUserModelMapper.selectByUserId(userId);
        if(!CollectionUtils.isEmpty(roleUserModelList)){
            boolean result = roleUserModelList.stream().anyMatch(role -> role.getRoleName().equals(Role.FINANCE.getValue()) || role.getRoleName().equals(Role.HR.getValue()));
            if(result){
                throw new BusinessException("人事、财务不能加入团队");
            }
        }
        TeamUserModel teamUserModel = new TeamUserModel();
        teamUserModel.setType(Role.CUSTOMER_MGR.getValue());
        teamUserModel.setTeamId(teamId);
        teamUserModel.setUserId(userId);
        teamUserModel.setCreateTime(new Date());
        teamUserModel.setUpdateTime(new Date());
        teamUserModelMapper.insert(teamUserModel);
    }
    
    @Transactional
    public int joinTeam(String teamId, String[] usernames){
        Assert.notNull(teamId, "teamId is null");
        Assert.notNull(usernames, "usernames is null");
        if(teamModelMapper.selectByTeamId(teamId) == null){
            throw new BusinessException("该团队不存在");
        }
        int result = 0;
        for(String username:usernames) {
        	username = username.replaceAll("\\r", "");
        	username = username.trim();
	        UserModel userModel = userModelMapper.selectByUsername(username);
	        if(userModel == null ){
	            continue;
	        }
	        if(!UserStatus.ENABLE.equals(userModel.getStatus())){
	            continue;
	        }
	        List<TeamWithRoleTypeModel> teamWithRoleTypeList = teamModelMapper.selectByUserId(userModel.getUserId());
	        if(!CollectionUtils.isEmpty(teamWithRoleTypeList)){
	            continue;
	        }
	     
	        TeamUserModel tum = teamUserModelMapper.selectByTeamIdUserId(teamId, userModel.getUserId());
	        if(tum == null) {
		        TeamUserModel teamUserModel = new TeamUserModel();
		        teamUserModel.setType(Role.CUSTOMER_MGR.getValue());
		        teamUserModel.setTeamId(teamId);
		        teamUserModel.setUserId(userModel.getUserId());
		        teamUserModel.setCreateTime(new Date());
		        teamUserModel.setUpdateTime(new Date());
		        if(teamUserModelMapper.insert(teamUserModel)>0) {
		        	++result;
		        }
	        }
        }
        return result;
    }
    
    public String selectTeamNameByUserId(String userId) {
    	return teamUserModelMapper.selectTeamNameByUserId(userId);
    }

}

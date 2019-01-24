package com.sumslack.web.simple.restful;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sumslack.web.simple.constant.Role;
import com.sumslack.web.simple.model.TeamModel;
import com.sumslack.web.simple.restful.response.ResponseUtils;
import com.sumslack.web.simple.service.TeamService;
import com.sumslack.web.simple.utils.BusinessUtils;

@RestController
@RequestMapping(value = "/team/", produces = {MediaType.APPLICATION_JSON_VALUE})
public class TeamController {

    @Autowired
    private TeamService teamService;

    @RequestMapping(value = "/queryAll", method = RequestMethod.POST)
    public Object queryAll(String draw) {
        List<TeamModel> teamList = teamService.queryAllTeam();
        Map<String, Object> ret = new HashMap<>();
        ret.put("recordsTotal", teamList.size());
        ret.put("recordsFiltered", teamList.size());
        ret.put("data", teamList);
        ret.put("draw", draw);
        return ret;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Object createTeam(String name, String description, HttpServletRequest request){
        BusinessUtils.checkAdminSession(request);
        teamService.createTeam(name,description);
        return ResponseUtils.ok(null);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Object deleteTeam(String teamId, HttpServletRequest request){
        BusinessUtils.checkAdminSession(request);
        teamService.deleteTeam(teamId);
        return ResponseUtils.ok(null);
    }

    @RequestMapping(value = "/join", method = RequestMethod.POST)
    public Object joinTeam(String teamId, String userId, HttpServletRequest request){
        BusinessUtils.checkAdminSession(request);
        teamService.joinTeam(teamId, userId);
        return ResponseUtils.ok(null);
    }
    
    @RequestMapping(value = "/joinByUsernames", method = RequestMethod.POST)
    public Object joinTeamByUserNames(String teamId, String userNames, HttpServletRequest request){
        BusinessUtils.checkAdminSession(request);
        String[] userNameList = userNames.split("\\n");
        int result = teamService.joinTeam(teamId, userNameList);
        return ResponseUtils.ok(result);
    }
    
    

    @RequestMapping(value = "/removeMember", method = RequestMethod.POST)
    public Object removeTeamMember(String teamId, String userId, HttpServletRequest request){
        BusinessUtils.checkAdminSession(request);
        teamService.removeTeamMember(teamId, userId);
        return ResponseUtils.ok(null);
    }


    @RequestMapping(value = "/setTeamManagerRole", method = RequestMethod.POST)
    public Object setTeamManagerRole(String teamId, String userId, HttpServletRequest request){
        BusinessUtils.checkAdminSession(request);
        teamService.updateTeamMemberRole(teamId, userId, Role.TEAM_MGR.getValue());
        return ResponseUtils.ok(null);
    }

    @RequestMapping(value = "/setCustomerManagerRole", method = RequestMethod.POST)
    public Object setTeamManager(String teamId, String userId, HttpServletRequest request){
        BusinessUtils.checkAdminSession(request);
        teamService.updateTeamMemberRole(teamId, userId, Role.CUSTOMER_MGR.getValue());
        return ResponseUtils.ok(null);
    }


}

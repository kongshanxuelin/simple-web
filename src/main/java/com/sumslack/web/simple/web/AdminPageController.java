package com.sumslack.web.simple.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sumslack.web.simple.model.TeamModel;
import com.sumslack.web.simple.model.UserModel;
import com.sumslack.web.simple.service.TeamService;
import com.sumslack.web.simple.service.UserService;

@Controller
@RequestMapping(value = "/admin/")
public class AdminPageController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private TeamService teamService;
    

    @GetMapping("/teamManage")
    public String teamManage(){
        return "admin/team-manage";
    }

    @GetMapping("/teamMemberManage")
    public String teamMemberManage(String teamId,Map<String, Object> model){
        model.put("teamId", teamId);
        return "admin/team-member-manage";
    }
    
    @GetMapping("/teamMemberBatchManage")
    public String teamMemberBatchManage(String teamId,Map<String, Object> model){
    	TeamModel team = teamService.getTeam(teamId);
    	if(team!=null ) {
    		model.put("team", team);
    	}
        model.put("teamId", teamId);
        return "admin/team-member-batch-manage";
    }

    @GetMapping("/teamCreate")
    public String teamCreate(Map<String, Object> model){
        return "admin/team-create";
    }
    
    @GetMapping("/productManage")
    public String productManage(){
        return "admin/product-manage";
    }

    @GetMapping("/userManage")
    public String userManage(){
        return "admin/user-manage";
    }

    @GetMapping("/userCreate")
    public String userCreate(Map<String, Object> model){
        return "admin/user-create";
    }
    
    @GetMapping("/useresCreate")
    public String useresCreate(Map<String, Object> model){
        return "admin/user-batch-create";
    }
    @GetMapping("/userUpdate")
    public String userUpdate(String userId, Map<String, Object> model){
        UserModel user = userService.getByUserId(userId);
        model.put("user", user);
        return "admin/user-update";
    }
    
    @GetMapping("/test")
    public String test(String userId, Map<String, Object> model){
        UserModel user = userService.getByUserId(userId);
        model.put("user", user);
        return "admin/test";
    }
}

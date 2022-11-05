package com.boot.webapp4springbootgradle.controller;

import com.boot.webapp4springbootgradle.model.RoleMaster;
import com.boot.webapp4springbootgradle.service.RoleMasterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class RoleMasterController {
    @Autowired
    RoleMasterService roleMasterService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/role/role_search.view", method = RequestMethod.GET)
    public String roleSearchView(Model model) {
        logger.debug("roleSearchView");
        return "/role/role_list";
    }

    @RequestMapping(value = "/role/role_search.formdata", method = RequestMethod.POST)
    public String roleSearch(@RequestParam Map<String, String> param, Model model) {
        logger.debug("roleSearch");

        String roleId = param.get("roleId");
        String roleNm = param.get("roleNm");
        String useYn = param.get("useYn");

        List<RoleMaster> list = roleMasterService.findByRoleIdContainsIgnoreCaseAndRoleNmContainsIgnoreCaseAndUseYnContainsIgnoreCase(roleId, roleNm, useYn);
        model.addAttribute("list", list);

        return "/role/role_list";
    }

    @RequestMapping(value = "/role/role_insert.view", method = RequestMethod.GET)
    public String roleInsertView(Model model) {
        logger.debug("roleInsertView");

        RoleMaster roleMaster = new RoleMaster();
        model.addAttribute("roleMaster", roleMaster);

        return "/role/role_insert";
    }

    @RequestMapping(value = "/role/role_insert.formdata", method = RequestMethod.POST)
    public String roleInsertFormdata(@RequestParam Map<String, String> param, Model model) {
        logger.debug("roleInsertFormdata");

        String roleId = param.get("roleId");
        String roleNm = param.get("roleNm");
        String roleDesc = param.get("roleDesc");
        String useYn = param.get("useYn");
        logger.debug("roleId=" + roleId);
        logger.debug("roleNm=" + roleNm);
        logger.debug("roleDesc=" + roleDesc);
        logger.debug("useYn=" + useYn);

        RoleMaster roleMaster = roleMasterService.findById(roleId);
        roleMaster.roleId = roleId;
        roleMaster.roleNm = roleNm;
        roleMaster.roleDesc = roleDesc;
        roleMaster.useYn = useYn;
        logger.debug(roleMaster.toString());

        int count = roleMasterService.save(roleMaster);
        logger.debug("count=" + count);

        List<RoleMaster> list = roleMasterService.findAll();
        model.addAttribute("list", list);

        return "/role/role_list";
    }

    @RequestMapping(value = "/role/role_update.view", method = RequestMethod.GET)
    public String roleUpdateView(@RequestParam Map<String, String> param, Model model) {
        String roleId = param.get("roleId");
        RoleMaster roleMaster = roleMasterService.findById(roleId);

        model.addAttribute("roleMaster", roleMaster);
        return "/role/role_insert";
    }

    @ResponseBody
    @RequestMapping(value = "/role/role_delete.json", method = RequestMethod.POST)
    public Map<String, String> roleDeleteJson(@RequestBody Map<String, String> param) {
        String roleId = param.get("roleId");
        int cnt = roleMasterService.deleteById(roleId);

        Map<String, String> result = new HashMap<>();
        if (cnt > 0) {
            result.put("result", "OK");
            result.put("msg", "SUCCESS");
        } else {
            result.put("result", "NOT_OK");
            result.put("msg", "FAIL");
        }

        return result;
    }
}


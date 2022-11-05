package com.boot.webapp4springbootgradle.controller;

import com.boot.webapp4springbootgradle.model.DeptMaster;
import com.boot.webapp4springbootgradle.service.DeptMasterService;
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
public class DeptMasterController {
    @Autowired
    DeptMasterService deptMasterService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/dept/dept_search.view", method = RequestMethod.GET)
    public String deptSearchView(Model model) {
        logger.debug("deptSearchView");
        return "/dept/dept_list";
    }

    @RequestMapping(value = "/dept/dept_search.formdata", method = RequestMethod.POST)
    public String deptSearch(@RequestParam Map<String, String> param, Model model) {
        logger.debug("deptSearch");

        String deptNm = param.get("deptNm");
        List<DeptMaster> list = deptMasterService.findByDeptNmContainsIgnoreCase(deptNm);
        model.addAttribute("list", list);

        return "/dept/dept_list";
    }

    @RequestMapping(value = "/dept/dept_insert.view", method = RequestMethod.GET)
    public String deptInsertView(Model model) {
        logger.debug("deptInsertView");

        DeptMaster deptMaster = new DeptMaster();
        model.addAttribute("deptMaster", deptMaster);

        return "/dept/dept_insert";
    }

    @RequestMapping(value = "/dept/dept_insert.formdata", method = RequestMethod.POST)
    public String deptInsert(@RequestParam Map<String, String> param, Model model) {
        logger.debug("deptInsert");

        String deptId = param.get("deptId");
        String deptNm = param.get("deptNm");
        String deptDesc = param.get("deptDesc");
        String upDeptId = param.get("upDeptId");
        String useYn = param.get("useYn");

        DeptMaster deptMaster = deptMasterService.findById(deptId);
        deptMaster.deptId = deptId;
        deptMaster.deptNm = deptNm;
        deptMaster.deptDesc = deptDesc;
        deptMaster.upDeptId = upDeptId;
        deptMaster.useYn = useYn;

        int count = deptMasterService.save(deptMaster);
        logger.debug("count=" + count);

        List<DeptMaster> list = deptMasterService.findAll();
        model.addAttribute("list", list);

        return "/dept/dept_list";
    }

    @RequestMapping(value = "/dept/dept_update.view", method = RequestMethod.GET)
    public String deptUpdateView(@RequestParam Map<String, String> param, Model model) {
        logger.debug("deptUpdateView");

        String deptId = param.get("deptId");
        logger.debug("deptId=", deptId);

        DeptMaster deptMaster = deptMasterService.findById(deptId);
        model.addAttribute("deptMaster", deptMaster);

        return "/dept/dept_insert";
    }

    @ResponseBody
    @RequestMapping(value = "/dept/dept_delete.json", method = RequestMethod.POST)
    public Map<String, String> deptDeleteJson(@RequestBody Map<String, String> param) {
        String deptId = param.get("deptId");
        int cnt = deptMasterService.deleteById(deptId);

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
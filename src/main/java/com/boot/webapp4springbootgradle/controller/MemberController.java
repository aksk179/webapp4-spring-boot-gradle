package com.boot.webapp4springbootgradle.controller;

import com.boot.webapp4springbootgradle.model.DeptMaster;
import com.boot.webapp4springbootgradle.model.Member;
import com.boot.webapp4springbootgradle.model.RoleMaster;
import com.boot.webapp4springbootgradle.security.User;
import com.boot.webapp4springbootgradle.service.DeptMasterService;
import com.boot.webapp4springbootgradle.service.MemberService;
import com.boot.webapp4springbootgradle.service.RoleMasterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;


@Controller
public class MemberController {

    @Autowired
    MemberService memberService;

    @Autowired
    DeptMasterService deptMasterService;

    @Autowired
    RoleMasterService roleMasterService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String goHome(Model model) {
        return "/index";
    }

    @RequestMapping(value = "/admin/member_list", method = RequestMethod.GET)
    public String memberList(Model model) {
        return "/admin/member_list";
    }

    @RequestMapping(value = "/admin/search_member_list.formdata", method = RequestMethod.POST)
    public String searchMemberListFormdata(@RequestParam Map<String, String> param, Model model) {

        String userName = param.get("userName");
        String roleId = param.get("roleId");

        List<Member> list = memberService.findByUserNameContainsIgnoreCaseAndRoleIdContainsIgnoreCase(userName, roleId);
        for (Member member : list) {
            logger.debug(member.userId);
            logger.debug(member.userName);
            logger.debug(member.deptMaster.deptNm);
            logger.debug("==============================");
        }

        model.addAttribute("list", list);

        return  "/admin/member_list";
    }


    @RequestMapping(value = "/admin/update_member.view", method = RequestMethod.GET)
    public String updateMemberView(@RequestParam Map<String, String> param, Locale locale, Model model) {
        logger.debug("1.updateMemberView() {}", param);

        //STEP-01 : 저장
        String userId = param.get("userId");
        Member member = memberService.findById(userId);

        List<DeptMaster> deptList = deptMasterService.findByUseYn("Y");
        List<RoleMaster> roleList = roleMasterService.findByUseYn("Y");

        //model객체를 이용해서 view로 Data전달
        model.addAttribute("member", member);
        model.addAttribute("deptList", deptList);
        model.addAttribute("roleList", roleList);

        return "/admin/insert_member_for_admin";
    }


    @RequestMapping(value = "/admin/delete_member.formdata", method = RequestMethod.GET)
    public  String deleteMemberFormdata(@RequestParam String userId, Model model) {
        int cnt = memberService.deleteById(userId);

        List<Member> list = memberService.findAll();
        model.addAttribute("list", list);

        return "/admin/member_list";
    }


    @RequestMapping(value = "/admin/approve_member.formdata", method = RequestMethod.GET)
    public String approveMemberFormdata(@RequestParam Map<String, String> param, Locale locale, Model model) {
        logger.debug("1.approveMemberFormdata() {}", param);

        //STEP-01 : 저장
        String userId = param.get("userId");
        Member member = memberService.findById(userId);
        member.useYn = "Y";

        int cnt = memberService.save(member);

        List<Member> list = memberService.findAll();

        //model객체를 이용해서 view로 Data전달
        model.addAttribute("list", list);

        return "/admin/member_list";
    }



    @RequestMapping(value = "/user/insert_member_for_user", method = RequestMethod.GET)
    public String insertMemberForUser(Model model) {
        return "/user/insert_member_for_user";
    }



    @RequestMapping(value = "/admin/insert_member_for_admin", method = RequestMethod.GET)
    public String insertMemberForAdmin(Model model) {
        Member member = new Member();

        List<DeptMaster> deptList = deptMasterService.findByUseYn("Y");
        List<RoleMaster> roleList = roleMasterService.findByUseYn("Y");

        model.addAttribute("member", member);
        model.addAttribute("deptList", deptList);
        model.addAttribute("roleList", roleList);

        return "/admin/insert_member_for_admin";
    }

    @RequestMapping(value = "/admin/insert_member_for_admin.formdata", method = RequestMethod.POST)
    public String insertMemberForAdminFormdata(@RequestParam Map<String, String> param, Model model) {

        logger.debug("1.insertMemberForAdminFormdata() {}", param);

        //step-01 : 저장
        String userId = param.get("userId");
        String userName = param.get("userName");
        String userPwd = param.get("userPwd");
        String cellPhone = param.get("cellPhone");
        String useYn = param.get("useYn");
        String address = param.get("address");
        String deptId = param.get("deptId");
        String roleId = param.get("roleId");

        Member member = memberService.findById(userId);
        member.userId = userId;
        member.userName = userName;
        member.userPwd = userPwd;
        member.cellPhone = cellPhone;
        member.useYn = useYn;
        member.address = address;
        member.deptId = deptId;
        member.roleId = roleId;

        int count = memberService.save(member);

        //STEP-02 : 조회
        List<Member> list = memberService.findAll();
        model.addAttribute("list", list);

        return "/admin/member_list";
    }

    @RequestMapping(value = "/login/login", method = RequestMethod.GET)
    public String login(Model model) {
        return "/login/login";
    }

    @RequestMapping(value = "/login/login.formdata", method = RequestMethod.POST)
    public String loginFormdata(@RequestParam Map<String, String> param, Model model) {

        logger.debug("loginFormdata()");
        String userId = param.get("userId");
        String userPwd = param.get("userPwd");

        logger.debug("userId="+userId);
        logger.debug("userPwd="+userPwd);
        Member member = memberService.findById(userId);

        if (member == null) {
            model.addAttribute("result", "NO_ID");
            return "/login/login";
        } else {
            if (userPwd.equals(member.userPwd)) {
                model.addAttribute("result", "SUCCESS");
                return "/admin/member_list";
            } else {
                model.addAttribute("result", "FAIL");
                return "/login/login";
            }
        }

    }

    @RequestMapping(value = "/login/register", method = RequestMethod.GET)
    public String register(Model model) {
        logger.debug("register OK?");
        Member member = new Member();
        model.addAttribute("member", member);
        return "/login/register";
    }



    @ResponseBody
    @RequestMapping(value = "/login/check_id.json", method = RequestMethod.POST)
    public Map checkIdJson(@RequestBody Map<String, String> param) {
        String userId = param.get("userId");
        Member member = memberService.findById(userId);
        Map<String, String> result = new HashMap<>();
        if (member == null) {
            result.put("result", "OK");
            result.put("msg", "SUCCESS");
        } else {
            result.put("result", "NOT_OK");
            result.put("msg", "FAIL");
        }
        return result;
    }



    @RequestMapping(value = "/login/register.formdata", method = RequestMethod.POST)
    public String registerFormdata(@RequestParam Map<String, String> param, Model model) {
        logger.debug("registerFormdata()");

        String userId = param.get("userId");
        String userName = param.get("userName");
        String userPwd = param.get("userPwd");
        String cellPhone = param.get("cellPhone");

        Member member = new Member();
        member.userId = userId;
        member.userName = userName;
        member.userPwd = userPwd;
        member.cellPhone = cellPhone;
        member.roleId = "USER";
        member.useYn = "N";

        int cnt = memberService.save(member);

        return "/login/login";
    }

    @RequestMapping(value = "/user/update_for_user", method = RequestMethod.GET)
    public String updateForUser(Model model) {
        logger.debug("updateforuser");

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = (User)principal;
        String username = user.getUsername();

        Member member = memberService.findById(username);
        model.addAttribute("member", member);

        return "/user/update_for_user";
    }

    @RequestMapping(value = "/user/update_for_user.formdata", method = RequestMethod.POST)
    public String updateForUserFormdata(@RequestParam Map<String, String> param, Model model) {
        logger.debug("updateforuserformdata");

//        String userId = "sunny454";

        //step-01 : 저장
        String userId = param.get("userId");
        String userName = param.get("userName");
        String userPwd = param.get("userPwd");
        String cellPhone = param.get("cellPhone");
        String useYn = param.get("useYn");
        String deptId = param.get("deptId");

        Member member = memberService.findById(userId);
        member.userName = userName;
        member.userPwd = userPwd;
        member.cellPhone = cellPhone;
        member.useYn = useYn;
        member.deptId = deptId;

        logger.debug("member="+member.toString());

        int count = memberService.save(member);

        return "/index";
    }
}

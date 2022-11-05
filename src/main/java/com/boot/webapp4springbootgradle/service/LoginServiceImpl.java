package com.boot.webapp4springbootgradle.service;

import com.boot.webapp4springbootgradle.model.Member;
import com.boot.webapp4springbootgradle.repository.MemberRepository;
import com.boot.webapp4springbootgradle.security.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LoginServiceImpl implements UserDetailsService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        logger.debug("hi");

        User user = new User();

        Optional<Member> result = memberRepository.findById(userId);
        if (result.isPresent()) {
            Member member = result.get();
            logger.debug(member.toString());

            user.userId = member.userId;
            user.userPwd = "{noop}" + member.userPwd;
            user.userName = member.userName;
            user.cellPhone = member.cellPhone;
            user.address = member.address;
            user.emailId = member.emailId;
            user.deptId = member.deptId;
            user.roleId = member.roleId;
            user.birth = member.birth;
            user.useYn = member.useYn;
            user.regiDt = member.regiDt;
            user.apprDt = member.apprDt;
            if (member.useYn == "N") {
                user.isAccountNonExpired = false;
                user.isAccountNonLocked = false;
                user.isCredentialsNonExpired = false;
                user.isEnabled = false;
            } else {
                user.isAccountNonExpired = true;
                user.isAccountNonLocked = true;
                user.isCredentialsNonExpired = true;
                user.isEnabled = true;
            }

            List<GrantedAuthority> authorityList = new ArrayList<>();
            if ("ADMIN".equals(member.roleId)) {
                authorityList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            } else {
                authorityList.add(new SimpleGrantedAuthority("ROLE_USER"));
            }
            user.authorities = authorityList;
        } else {
            user = null;
        }
        return user;
    }
}

package com.boot.webapp4springbootgradle.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;

public class User implements UserDetails {

    private static final long serialVersionUID = 1L;

    public String userId;
    public String userPwd;

    public String userName;

    public String cellPhone;

    public String address;
    public String emailId;
    public String deptId;
    public String roleId;
    public LocalDateTime birth;
    public String useYn;
    public LocalDateTime regiDt;
    public LocalDateTime apprDt;
    public boolean isAccountNonExpired=true;
    public boolean isAccountNonLocked=true;
    public boolean isCredentialsNonExpired=true;
    public boolean isEnabled=true;
    public Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return userPwd;
    }

    @Override
    public String getUsername() {
        return userId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}

package com.boot.webapp4springbootgradle.service;

import com.boot.webapp4springbootgradle.model.RoleMaster;

import java.util.List;

public interface RoleMasterService {
    List<RoleMaster> findAll();

    List<RoleMaster> findByRoleIdContainsIgnoreCaseAndRoleNmContainsIgnoreCaseAndUseYnContainsIgnoreCase(String roleId, String roleNm, String useYn);

    List<RoleMaster> findByUseYn(String useYn);

    int save(RoleMaster roleMaster);

    RoleMaster findById(String roleId);

    int deleteById(String roleId);
}

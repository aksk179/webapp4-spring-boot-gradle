package com.boot.webapp4springbootgradle.service;

import com.boot.webapp4springbootgradle.model.RoleMaster;
import com.boot.webapp4springbootgradle.repository.RoleMasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleMasterServiceImpl implements RoleMasterService{
    @Autowired
    RoleMasterRepository roleMasterRepository;

    @Override
    public List<RoleMaster> findAll() {
        return roleMasterRepository.findAll();
    }

    @Override
    public List<RoleMaster> findByRoleIdContainsIgnoreCaseAndRoleNmContainsIgnoreCaseAndUseYnContainsIgnoreCase(String roleId, String roleNm, String useYn) {
        return roleMasterRepository.findByRoleIdContainsIgnoreCaseAndRoleNmContainsIgnoreCaseAndUseYnContainsIgnoreCase(roleId, roleNm, useYn);
    }

    @Override
    public List<RoleMaster> findByUseYn(String useYn) {
        return roleMasterRepository.findByUseYn(useYn);
    }

    @Override
    public int save(RoleMaster roleMaster) {
        RoleMaster result = roleMasterRepository.save(roleMaster);
        return 1;
    }

    @Override
    public RoleMaster findById(String roleId) {
        RoleMaster result = roleMasterRepository.findById(roleId).orElse(new RoleMaster());
        return result;
    }

    @Override
    public int deleteById(String roleId) {
        roleMasterRepository.deleteById(roleId);
        return 1;
    }
}

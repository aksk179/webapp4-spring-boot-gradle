package com.boot.webapp4springbootgradle.repository;

import com.boot.webapp4springbootgradle.model.RoleMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMasterRepository extends JpaRepository<RoleMaster, String> {
    List<RoleMaster> findByRoleIdContainsIgnoreCaseAndRoleNmContainsIgnoreCaseAndUseYnContainsIgnoreCase(String roleId, String roleNm, String UseYn);

    List<RoleMaster> findByUseYn(String useYn);
}
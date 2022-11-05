package com.boot.webapp4springbootgradle.service;

import com.boot.webapp4springbootgradle.model.DeptMaster;

import java.util.List;

public interface DeptMasterService {
    List<DeptMaster> findAll();

    List<DeptMaster> findByDeptNmContainsIgnoreCase(String deptNm);

    List<DeptMaster> findByUseYn(String useYn);

    int save(DeptMaster deptMaster);

    DeptMaster findById(String deptId);

    int deleteById(String deptId);
}

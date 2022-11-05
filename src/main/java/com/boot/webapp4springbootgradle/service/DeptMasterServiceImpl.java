package com.boot.webapp4springbootgradle.service;

import com.boot.webapp4springbootgradle.model.DeptMaster;
import com.boot.webapp4springbootgradle.repository.DeptMasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptMasterServiceImpl implements DeptMasterService{

    @Autowired
    DeptMasterRepository deptMasterRepository;

    @Override
    public List<DeptMaster> findAll() { return deptMasterRepository.findAll(); }

    @Override
    public List<DeptMaster> findByDeptNmContainsIgnoreCase(String deptNm) {
        return deptMasterRepository.findByDeptNmContainsIgnoreCase(deptNm);
    }

    @Override
    public List<DeptMaster> findByUseYn(String useYn) {
        return deptMasterRepository.findByUseYn(useYn);
    }

    @Override
    public int save(DeptMaster deptMaster) {
        DeptMaster result = deptMasterRepository.save(deptMaster);
        return 1;
    }

    @Override
    public DeptMaster findById(String deptId) {
        DeptMaster result = deptMasterRepository.findById(deptId).orElse(new DeptMaster());
        return result;
    }

    @Override
    public int deleteById(String deptId) {
        deptMasterRepository.deleteById(deptId);
        return 1;
    }
}

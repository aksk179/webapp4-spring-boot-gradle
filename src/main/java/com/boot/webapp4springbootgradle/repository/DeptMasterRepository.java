package com.boot.webapp4springbootgradle.repository;

import com.boot.webapp4springbootgradle.model.DeptMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeptMasterRepository extends JpaRepository<DeptMaster, String> {
    List<DeptMaster> findByDeptNmContainsIgnoreCase(String deptNm);
    List<DeptMaster> findByUseYn(String useYn);
}

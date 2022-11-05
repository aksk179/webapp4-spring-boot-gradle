package com.boot.webapp4springbootgradle.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
public class DeptMaster {
    @Id
    @Column(length=10, nullable=false, name = "dept_id")
    public String deptId;

    @Column(length = 30)
    public String deptNm;

    @Column(length = 100)
    public String deptDesc;
    @Column(length = 100)
    public String upDeptId;

    @Column(length = 1)
    public String useYn;

    @CreationTimestamp
    public LocalDateTime crDt;

    @Override
    public String toString() {
        return deptId + ", " + deptNm + ", " + deptDesc + ", " + useYn + ", " + crDt;
    }
}

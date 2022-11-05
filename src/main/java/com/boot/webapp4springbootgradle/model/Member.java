package com.boot.webapp4springbootgradle.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class Member {
    @Id
    @Column(length=10, nullable=false)
    public String userId;

    @Column(length = 50)
    public String userPwd;

    @Column(length = 50)
    public String userName;

    @Column(length = 20)
    public String cellPhone;

    @Column(length = 100)
    public String address;

    @Column(length = 50)
    public String emailId;

    @Column(length = 10, name = "dept_id")
    public String deptId;

    @ManyToOne
    @JoinColumn(name = "dept_id", insertable = false, updatable = false)
    public DeptMaster deptMaster;

    @ManyToOne
    @JoinColumn(name = "role_id", insertable = false, updatable = false)
    public RoleMaster roleMaster;

    @Column(length = 10, name = "role_id")
    public String roleId;

    public LocalDateTime birth;

    @Column(length = 50)
    public String useYn;

    @CreationTimestamp
    public LocalDateTime regiDt;

    @CreationTimestamp
    public LocalDateTime apprDt;

    @Override
    public String toString() {
        return userId + ", " + userName + ", " + cellPhone + ", " + useYn + ", " + regiDt + ", " + roleId;
    }
}

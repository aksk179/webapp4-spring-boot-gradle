package com.boot.webapp4springbootgradle.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
public class RoleMaster {
    @Id
    @Column(length=10, nullable=false)
    public String roleId;

    @Column(length = 30)
    public String roleNm;

    @Column(length = 100)
    public String roleDesc;

    @Column(length = 1)
    public String useYn;

    @CreationTimestamp
    public LocalDateTime crDt;

    @Override
    public String toString() {
        return roleId + ", " + roleNm + ", " + roleDesc + ", " + useYn + ", " + crDt;
    }
}

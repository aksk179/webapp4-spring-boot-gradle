package com.boot.webapp4springbootgradle.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Sample {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int eno;

    public String name;
    public int age;
    public String address;
    public String cellPhone;

}

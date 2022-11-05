package com.boot.webapp4springbootgradle.service;

import com.boot.webapp4springbootgradle.model.Sample;

import java.util.List;

public interface SampleService {
    List<Sample> findAll();
    List<Sample> findByName(String name);
    List<Sample> findByNameContainsIgnoreCase(String name);
    List<Sample> findByNameContainsIgnoreCaseAndAddressContainsIgnoreCase(String name, String address);
    List<Sample> findByNameAndAddress(String name, String address);
    int save(Sample sample);
    Sample findById(int eno);
    int deleteById(int eno);
}
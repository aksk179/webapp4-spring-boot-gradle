package com.boot.webapp4springbootgradle.repository;

import com.boot.webapp4springbootgradle.model.Sample;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SampleRepository extends JpaRepository<Sample, Integer> {
    List<Sample> findByName(String name);
    List<Sample> findByNameContainsIgnoreCase(String name);
    List<Sample> findByNameContainsIgnoreCaseAndAddressContainsIgnoreCase(String name, String address);
    List<Sample> findByNameAndAddress(String name, String address);
}

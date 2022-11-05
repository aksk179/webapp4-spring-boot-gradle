package com.boot.webapp4springbootgradle.service;

import com.boot.webapp4springbootgradle.model.Sample;
import com.boot.webapp4springbootgradle.repository.SampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SampleServiceImpl implements SampleService {

    @Autowired
    SampleRepository repository;

    @Override
    public List<Sample> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Sample> findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public List<Sample> findByNameContainsIgnoreCase(String name) {
        return repository.findByNameContainsIgnoreCase(name);
    }

    @Override
    public List<Sample> findByNameContainsIgnoreCaseAndAddressContainsIgnoreCase(String name, String address) {
        return repository.findByNameContainsIgnoreCaseAndAddressContainsIgnoreCase(name, address);
    }

    @Override
    public List<Sample> findByNameAndAddress(String name, String address) {
        return repository.findByNameAndAddress(name, address);
    }

    @Override
    public int save(Sample sample) {
        Sample result = repository.save(sample);
        return 1;
    }

    @Override
    public Sample findById(int eno) {
        Optional<Sample> result = repository.findById(eno);
        return result.get();
    }

    @Override
    public int deleteById(int eno) {
        repository.deleteById(eno);
        return 1;
    }
}

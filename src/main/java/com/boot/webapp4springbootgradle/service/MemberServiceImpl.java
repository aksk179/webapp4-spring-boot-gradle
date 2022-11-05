package com.boot.webapp4springbootgradle.service;


import com.boot.webapp4springbootgradle.model.Member;
import com.boot.webapp4springbootgradle.repository.MemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    MemberRepository repository;

    @Override
    public List<Member> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Member> findByUserNameContainsIgnoreCaseAndAddressContainsIgnoreCase(String userName, String address) {
        return repository.findByUserNameContainsIgnoreCaseAndAddressContainsIgnoreCase(userName, address);
    }

    @Override
    public List<Member> findByUserNameContainsIgnoreCaseAndRoleIdContainsIgnoreCase(String userName, String roleId) {
        return repository.findByUserNameContainsIgnoreCaseAndRoleIdContainsIgnoreCase(userName, roleId);
    }

    @Override
    public List<Member> findByRegiDtBetween(LocalDateTime start, LocalDateTime end) {
        return repository.findByRegiDtBetween(start, end);
    }

    @Override
    public int save(Member member) {
        Member result = repository.saveAndFlush(member);
        return 1;
    }

    @Override
    public Member findById(String userId) {
        Optional<Member> result = repository.findById(userId);
        if (result.isPresent()) {
            return result.get();
        } else {
            return new Member();
        }
    }

    @Override
    public int deleteById(String userId) {
        repository.deleteById(userId);
        return 1;
    }
}

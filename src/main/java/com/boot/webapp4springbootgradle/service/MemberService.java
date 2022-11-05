package com.boot.webapp4springbootgradle.service;

import com.boot.webapp4springbootgradle.model.Member;

import java.time.LocalDateTime;
import java.util.List;

public interface MemberService {
    List<Member> findAll();

    List<Member> findByUserNameContainsIgnoreCaseAndAddressContainsIgnoreCase(String userName, String address);

    List<Member> findByUserNameContainsIgnoreCaseAndRoleIdContainsIgnoreCase(String userName, String roleId);

    List<Member> findByRegiDtBetween(LocalDateTime start, LocalDateTime end);

    int save(Member member);

    Member findById(String userId);

    int deleteById(String userId);
}

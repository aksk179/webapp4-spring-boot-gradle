package com.boot.webapp4springbootgradle.repository;

import com.boot.webapp4springbootgradle.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface MemberRepository extends JpaRepository<Member, String> {
    List<Member> findByUserNameContainsIgnoreCaseAndAddressContainsIgnoreCase(String name, String address);
    List<Member> findByRegiDtBetween(LocalDateTime start, LocalDateTime end);

    List<Member> findByUserNameContainsIgnoreCaseAndRoleIdContainsIgnoreCase(String userName, String roleId);
}

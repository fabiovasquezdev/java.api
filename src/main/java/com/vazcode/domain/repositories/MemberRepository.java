package com.vazcode.domain.repositories;

import com.vazcode.domain.entities.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id);
    List<Member> findAll();
    void delete(Member member);
    boolean existsByEmail(String email);
    Optional<Member> findByEmail(String email);
}
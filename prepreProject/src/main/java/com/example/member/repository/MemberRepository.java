package com.example.member.repository;

import com.example.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

//JpaRepository<K, Id> (K : 엔티티 클래스, Id : @Id 붙은 변수 타입)
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);
}

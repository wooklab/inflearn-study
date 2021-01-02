package hello.hellospring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import hello.hellospring.domain.Member;

// 기본적인 CRUD는 JpaRepository에서 제공
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    // JPQL select m from Member m where m.name = ?
    @Override
    Optional<Member> findByName(String name);
}
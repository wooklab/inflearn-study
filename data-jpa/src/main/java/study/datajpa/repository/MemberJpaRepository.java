package study.datajpa.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import study.datajpa.entity.Member;

@Repository
public class MemberJpaRepository {

    @PersistenceContext
    private EntityManager em;

    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    // JPA 는 자동변경 감지라는 기능으로 변경을 자동으로 감지하여 update query를 수행함. (별도 update기능 불필요)
    // public void update(Member member) { }

    public void delete(Member member) {
        em.remove(member);
    }

    public List<Member> findAll() {
        // JPQL(객체를 대상으로 하는 쿼리)
        return em.createQuery("select m from Member m", Member.class)
            .getResultList();   // 반환하는 결과 값이 복수
    }

    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    public long count() {
        return em.createQuery("select count(m) from Member m", Long.class)
            .getSingleResult(); // 반환하는 결과 값이 단수
    }

    public Member find(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findByUsernameAndAgeGreaterThan(String username, int age) {
        return em.createQuery("select m from Member m where m.username = :username and m.age > :age")
                .setParameter("username", username)
                .setParameter("age", age)
                .getResultList();
    }
}

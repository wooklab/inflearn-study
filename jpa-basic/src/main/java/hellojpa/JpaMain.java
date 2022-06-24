package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member = new Member();
            member.setUsername("hello");

            em.persist(member);

            em.flush();
            em.clear();

            // join 쿼리로 member와 team을 찾음
//            Member findMember = em.find(Member.class, member.getId());
            Member findMember = em.getReference(Member.class, member.getId());  // 해당 단계에선 아무것도 출력되지 않음
            System.out.println("findMember = " + findMember.getClass());    // findMember = class hellojpa.Member$HibernateProxy$Ndn8OAFg
            System.out.println("findMember.id = " + findMember.getId());
            System.out.println("findMember.username = " + findMember.getUsername());    // 해당 단계에서 DB에 쿼리
            System.out.println("findMember.username = " + findMember.getUsername());    // 이미 초기화 후에는 쿼리 불필요

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}

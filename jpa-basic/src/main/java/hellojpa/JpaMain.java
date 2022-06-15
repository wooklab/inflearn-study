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
            // 영속
            Member member = new Member(200L, "member200");
            em.persist(member);

            // 아래와 같이 직접 호출 하거나 commit 시 & JPQL 실행시 자동 실행
            em.flush(); // db 쿼리가 바로 실행 (1차 캐시를 지우거나 하는 게 아니라 SQL 저장소에 저장된 데이터를 처리한다)

            System.out.println("====================");
            tx.commit();    // 트랜잭션만 처리
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}

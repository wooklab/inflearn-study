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
            Member member1 = new Member(151L, "A");
            Member member2 = new Member(161L, "B");

            em.persist(member1);
            em.persist(member2);

            System.out.println("====================");

            // 쿼리가 2개 실행됨 (버퍼링가능)
            tx.commit();    // 실제 쿼리가 발생하는 시점
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}

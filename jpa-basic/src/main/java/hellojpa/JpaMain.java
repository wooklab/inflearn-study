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
            Member member = em.find(Member.class, 150L);
            member.setName("ZZZZZ");

//            em.persist(member); // ?? 이렇게 하지 않아도 된다. 컬렉션에 값을 꺼내고 변경하고 다시 집어 넣지 않는 원리와 같다.

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

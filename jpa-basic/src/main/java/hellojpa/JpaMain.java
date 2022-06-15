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
            member.setName("AAAAA");

            // 준영속 (영속성 컨텍스트의 이점을 사용할 수 없음)
//            em.detach(member);  // 특정 entity detach
            em.clear();         // 전체 entity 초기화
//            em.close();   // 영속성 컨텍스트 종료

            Member member2 = em.find(Member.class, 150L);   // 쿼리를 다시 전송

            System.out.println("====================");
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}

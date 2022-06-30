package jpql;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Member member = new Member();
            member.setUsername("member1");
            member.setAge(10);
            em.persist(member);

            em.flush();
            em.clear();

            // m.team -> return Team
            List<Team> result = em.createQuery("select m.team from Member m", Team.class)   // 자동 team join (묵시적 join)
                                  .getResultList();

            // 위와 동일한 결과
            // 아래처럼 명시적으로 join이 발생됨을 알려주도록 하는 것을 권장
            List<Team> result2 = em.createQuery("select t from Member m join m.team t", Team.class)   // 명시적 team join
                                   .getResultList();

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        emf.close();
    }
}

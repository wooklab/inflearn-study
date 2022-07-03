package jpql;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Collection;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Team team = new Team();
            em.persist(team);

            Member member1 = new Member();
            member1.setUsername("관리자1");
            member1.setTeam(team);
            em.persist(member1);
            Member member2 = new Member();
            member2.setUsername("관리자2");
            member2.setTeam(team);
            em.persist(member2);


            em.flush();
            em.clear();

            /*String query = "select m.username from Member m";     // 1.상태 필드 (탐색 끝)
            List<String> result = em.createQuery(query, String.class).getResultList();*/
            /*String query = "select m.team from Member m";         // 2.단일 값 연관 필드
            List<Team> result = em.createQuery(query, Team.class).getResultList();*/
            String query = "select t.members from Team t";          // 3.컬렉션 값 연관 필드
            Collection result = em.createQuery(query, Collection.class).getResultList();


            for (Object s : result) {
                System.out.println("s = " + s);
            }
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

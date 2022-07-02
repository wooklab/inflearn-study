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

            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("관리자");
            member.setAge(10);
            member.setType(MemberType.ADMIN);
            member.changeTeam(team);

            em.persist(member);

            em.flush();
            em.clear();

//            String query = "select concat('a', 'b') from Member m";
//            String query = "select 'a' || 'b' from Member m"; // 이것도 가능하지만 표준은 아님
            String query = "select size(t.members) from Team t";    // 연관관계 개수
            List<Integer> result = em.createQuery(query, Integer.class).getResultList();
            for (Integer s : result) {
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

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
            Movie movie = new Movie();
            movie.setDirector("aaaa");
            movie.setActor("bbbb");
            movie.setName("바람과 함께 사라지다");
            movie.setPrice(1_000);

            em.persist(movie);  // 알아서 테이블을 나눠서 INSERT

            em.flush();
            em.clear();

            NewItem newItem = em.find(NewItem.class, movie.getId()); // 명확하게 찾을떄는 효율적이지만, 그렇지 않을 떈 union all을 사용하게 되어 성능저하)
            System.out.println("newItem = " + newItem);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}

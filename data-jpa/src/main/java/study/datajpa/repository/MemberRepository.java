package study.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import study.datajpa.entity.Member;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findByUsernameAndAgeGreaterThan(String username, int age);

    /*
     * 먼저는 NamedQuery 를 찾고
     * 이 후에 method name 으로 유추해낸 쿼리를 생성한다.
     */
//    @Query(name = "Member.findByUsername")  // SpringJPA 에서 해당 어노테이션은 생략가능
    List<Member> findByUsername(@Param("username") String username);


    // 아래 정적 쿼리를 애플리케이션 로드하는 시점에 파싱을 하여 확인한다.
    @Query("select m from Member m where m.username = :username and m.age = :age")  // compile 시점에도 오류를 확인할 수 있다.
    List<Member> findUser(@Param("username") String username, @Param("age") int age);
}

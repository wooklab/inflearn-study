package study.datajpa.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import study.datajpa.entity.Member;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Rollback(value = false)
class MemberJpaRepositoryTest {

    @Autowired
    MemberJpaRepository memberJpaRepository;

    @Test
    void testMember() {
        Member member = new Member("memberA");
        Member savedMember = memberJpaRepository.save(member);

        Member findMember = memberJpaRepository.find(savedMember.getId());

        assertThat(findMember.getId()).isEqualTo(member.getId());
        assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        assertThat(findMember).isEqualTo(member);   // JPA 특성상 같은 트랜잭션상에 동일성을 보장 (1차캐시)
    }

    @Test
    void basicCRUD() {
        Member memeber1 = new Member("memeber1");
        Member memeber2 = new Member("memeber2");

        memberJpaRepository.save(memeber1);
        memberJpaRepository.save(memeber2);

        // 단건 조회 검증
        Member findMember1 = memberJpaRepository.findById(memeber1.getId()).get();
        Member findMember2 = memberJpaRepository.findById(memeber2.getId()).get();
        assertThat(findMember1).isEqualTo(memeber1);
        assertThat(findMember2).isEqualTo(memeber2);

        // 리스트 조회 검증
        List<Member> all = memberJpaRepository.findAll();
        assertThat(all.size()).isEqualTo(2);

        // 카운트 검증
        long count = memberJpaRepository.count();
        assertThat(count).isEqualTo(2);

        // 삭제 검증
        memberJpaRepository.delete(memeber1);
        memberJpaRepository.delete(memeber2);

        long deletedCount = memberJpaRepository.count();
        assertThat(deletedCount).isEqualTo(0);

    }

    @Test
    void findByUsernameAndAgeGreaterThen() {
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("AAA", 20);

        memberJpaRepository.save(m1);
        memberJpaRepository.save(m2);

        List<Member> result = memberJpaRepository.findByUsernameAndAgeGreaterThan("AAA", 15);

        assertThat(result.get(0).getUsername()).isEqualTo("AAA");
        assertThat(result.get(0).getAge()).isEqualTo(20);
        assertThat(result.size()).isEqualTo(1);
    }
}
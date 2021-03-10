package study.datajpa.entity;

import javax.persistence.*;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // private 하면 안됨.
@ToString(of = {"id", "username", "age"})   // team 은 제외. 서로 참조하여 무한 loop가 될 수 있음 (연관관계는 toString에서 제외하자)
@NamedQuery(    // 실무에서는 거의 안쓴는 NamedQuery, 하지만 애플리케이션 로드 시점에 쿼리 오류를 확인할 수 있어 유용한 기능도 있다.
        name = "Member.findByUsername",
        query = "select m from Member m where m.username = :username"
)
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String username;
    private int age;

    @ManyToOne(fetch = FetchType.LAZY)  // default=EAGER, 실무에서 성능향상을 위해 FetchType.LAZY 사용이 일반적
    @JoinColumn(name = "team_id")
    private Team team;

    public Member(String username) {
        this(username, 0);
    }

    public Member(String username, int age) {
        this(username, age, null);
    }

    public Member(String username, int age, Team team) {
        this.username = username;
        this.age = age;
        if (team != null) {
            this.team = team;
        }
    }

    public void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }
}

package hellojpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity // default (name = "Member")// JPA 가 내부적으로 구분하기 위한 이름
public class Member extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    // columnDefinition = "varchar(255)"
    @Column(name = "USERNAME") // DDL 생성기능으로 자동 스키마 생성시에만 작동
    private String username;

    @ManyToOne(fetch = FetchType.LAZY)  // FetchType.LAZY 옵션을 사용하면 일단 프록시로 조회
    @JoinColumn(name = "TEAM_ID")    // 일대다 에서 양방향(읽기전용)을 사용하고 싶은 경우 추가
    private Team team;

    protected Member() {
        // do nothing just for hibernate...
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}

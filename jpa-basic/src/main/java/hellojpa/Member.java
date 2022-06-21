package hellojpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity // default (name = "Member")// JPA 가 내부적으로 구분하기 위한 이름
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    // columnDefinition = "varchar(255)"
    @Column(name = "USERNAME") // DDL 생성기능으로 자동 스키마 생성시에만 작동
    private String username;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID", insertable = false, updatable = false)    // 일대다 에서 양방향(읽기전용)을 사용하고 싶은 경우 추가
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
}

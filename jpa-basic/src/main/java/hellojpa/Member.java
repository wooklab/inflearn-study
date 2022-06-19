package hellojpa;

import javax.persistence.*;

@Entity // default (name = "Member")// JPA 가 내부적으로 구분하기 위한 이름
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    // columnDefinition = "varchar(255)"
    @Column(name = "USERNAME") // DDL 생성기능으로 자동 스키마 생성시에만 작동
    private String username;

//    @Column(name = "TEAM_ID")
//    private Long teamId;

    @ManyToOne  // Member 입장에서 - Team 의 관계인 N:1
    @JoinColumn(name = "TEAM_ID")
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

    /*public void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }*/
}

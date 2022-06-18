package hellojpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity // default (name = "Member")// JPA 가 내부적으로 구분하기 위한 이름
@Table // (name = "MBR") // 추약어 사용시 이름을 명시하여 클래스명을 다르게 작성할 수 있다
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    // columnDefinition = "varchar(255)"
    @Column(name = "name", nullable = false) // DDL 생성기능으로 자동 스키마 생성시에만 작동
    private String username;

    public Member() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

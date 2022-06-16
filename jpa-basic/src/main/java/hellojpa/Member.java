package hellojpa;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity // default (name = "Member")// JPA 가 내부적으로 구분하기 위한 이름
@Table // (name = "MBR") // 추약어 사용시 이름을 명시하여 클래스명을 다르게 작성할 수 있다
public class Member {

    @Id
    private Long id;
    private String name;

    // JPA는 내부적으로 리플렉션 사용함으로 기본 생성자 필요(public이나 protected)
    public Member() {
    }

    public Member(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

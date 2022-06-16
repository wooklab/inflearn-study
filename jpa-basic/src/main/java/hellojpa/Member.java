package hellojpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity // default (name = "Member")// JPA 가 내부적으로 구분하기 위한 이름
@Table // (name = "MBR") // 추약어 사용시 이름을 명시하여 클래스명을 다르게 작성할 수 있다
public class Member {

    @Id
    private Long id;

    // columnDefinition = "varchar(255)"
    @Column(name = "name", nullable = false) // DDL 생성기능으로 자동 스키마 생성시에만 작동
    private String username;

    private Integer age;

    @Enumerated(EnumType.STRING)    // EnumType.ORDINAL 를 사용하면 위험하다. 내부적으로 이넘 순서가 바뀔수 있기 떄문
    private  RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    // java 8부터는 @Temporal 이 필요하지 않다.
    private LocalDate testLocalDate;
    private LocalDateTime testLocalDateTime;

    @Lob
    private String description;

    @Transient  // DB에서는 사용하지 않고 메모리에서만 사용할 경우
    private int temp;

    // JPA는 내부적으로 리플렉션 사용함으로 기본 생성자 필요(public이나 protected)
    public Member() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }
}

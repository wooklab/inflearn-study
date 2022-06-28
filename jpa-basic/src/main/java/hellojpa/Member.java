package hellojpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity // default (name = "Member")// JPA 가 내부적으로 구분하기 위한 이름
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    // columnDefinition = "varchar(255)"
    @Column(name = "USERNAME") // DDL 생성기능으로 자동 스키마 생성시에만 작동
    private String username;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    private String city;
    private String street;
    private String zipcode;

}

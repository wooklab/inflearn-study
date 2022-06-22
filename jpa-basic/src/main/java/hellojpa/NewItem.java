package hellojpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class NewItem {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private int price;
}

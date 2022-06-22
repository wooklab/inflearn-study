package hellojpa;

import javax.persistence.Entity;

@Entity
public class Book extends NewItem {

    private String author;
    private String isbn;
}

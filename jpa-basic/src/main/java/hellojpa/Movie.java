package hellojpa;

import javax.persistence.Entity;

@Entity
public class Movie extends NewItem {

    private String director;
    private String actor;
}

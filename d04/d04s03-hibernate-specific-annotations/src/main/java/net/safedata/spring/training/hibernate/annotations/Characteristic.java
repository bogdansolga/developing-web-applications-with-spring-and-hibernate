package net.safedata.spring.training.hibernate.annotations;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Characteristic {

    @Id
    private int id;

    @Column(name = "name", unique = true, nullable = false, insertable = true, updatable = false, length = 20)
    private String details;

    public Characteristic() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}

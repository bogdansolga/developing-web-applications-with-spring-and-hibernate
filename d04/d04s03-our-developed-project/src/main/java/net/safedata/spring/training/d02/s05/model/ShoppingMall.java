package net.safedata.spring.training.d02.s05.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@DiscriminatorValue("shoppingMall")
public class ShoppingMall extends Store {

    @Id
    private int id;

    @Column(length = 5)
    private double fanciness;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public double getFanciness() {
        return fanciness;
    }

    public void setFanciness(double fanciness) {
        this.fanciness = fanciness;
    }
}

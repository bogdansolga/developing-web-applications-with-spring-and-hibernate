package net.safedata.jpa.intro;

import net.safedata.spring.training.jpa.model.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(
        name = "Product"
        //schema = "spring_training"
)
@NamedQueries({
        @NamedQuery(
                name = "Product.bySection",
                query = "SELECT product " +
                        "FROM Product product " +
                        "WHERE product.storeSection.store.id = :storeId"
        ),

        @NamedQuery(
                name = "Product.getAll",
                query = "SELECT product " +
                        "FROM Product product"
        )
})
public class Product extends AbstractEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "product_sequence_generator")
    @SequenceGenerator(name = "product_sequence_generator", sequenceName="product_sequence",
            allocationSize = 1)
    private int id;

    @Column(name = "name", unique = true, nullable = false, insertable = true, updatable = false, length = 50)
    private String name;

    @ManyToOne(targetEntity = Section.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "sectionId")
    private Section section;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return id == product.id &&
                Objects.equals(name, product.name) &&
                Objects.equals(section, product.section);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, section);
    }

    @Override
    public String toString() {
        return id + ", " + name;
    }
}

package net.safedata.spring.training.hibernate.annotations;

import net.safedata.spring.training.Product;
import net.safedata.spring.training.hibernate.annotations.comparator.CharacteristicComparator;
import net.safedata.spring.training.hibernate.annotations.persister.CustomPersister;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Persister;
import org.hibernate.annotations.SortComparator;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import java.util.Set;

@Entity
@Immutable
@Persister(impl = CustomPersister.class)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("unused")
public class Phone extends Product {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "phone_sequence_generator")
    @SequenceGenerator(name = "phone_sequence_generator", sequenceName = "phone_sequence", allocationSize = 1)
    private int id;

    @NaturalId
    @Column(name = "name", unique = true, nullable = false, insertable = true, updatable = false, length = 20)
    @Where(clause = "producer.length > 10")
    private String producer;

    @NaturalId
    @Column(name = "model", unique = true, nullable = false, insertable = true, updatable = false, length = 20)
    private String model;

    @BatchSize(size = 5)
    @OneToMany
    @SortComparator(CharacteristicComparator.class)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Characteristic> characteristics;

    public Phone() {}

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Formula("if (producer === 'Apple') then 1000 else 100")
    public void getPrice() {}
}

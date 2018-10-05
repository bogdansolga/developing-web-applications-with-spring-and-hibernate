package net.safedata.spring.training.hibernate.annotations.comparator;

import net.safedata.spring.training.hibernate.annotations.Characteristic;

import java.text.Collator;
import java.util.Comparator;

public class CharacteristicComparator implements Comparator<Characteristic> {

    private static final Collator COLLATOR = Collator.getInstance();

    @Override
    public int compare(Characteristic o1, Characteristic o2) {
        return COLLATOR.compare(o1.getDetails(), o2.getDetails());
    }
}

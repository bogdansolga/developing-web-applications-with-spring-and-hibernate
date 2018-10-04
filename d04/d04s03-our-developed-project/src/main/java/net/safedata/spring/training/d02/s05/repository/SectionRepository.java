package net.safedata.spring.training.d02.s05.repository;

import net.safedata.spring.training.d02.s05.model.Section;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SectionRepository extends CrudRepository<Section, Integer> {

    @Query(
            "SELECT section FROM Section section " +
            "WHERE section.name = :name"
    )
    List<Section> getSectionsByName(@Param("name") final String name);

    Optional<List<Section>> findAllByNameContaining(@Param("text") final String text);
}

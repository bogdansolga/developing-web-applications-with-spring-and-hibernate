package net.safedata.spring.training.d02.s05.service;

import net.safedata.spring.training.d02.s05.model.Section;
import net.safedata.spring.training.d02.s05.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectionService {

    private final SectionRepository sectionRepository;

    @Autowired
    public SectionService(SectionRepository sectionRepository) {
        this.sectionRepository = sectionRepository;
    }

    public Iterable<Section> getAll() {
        return sectionRepository.findAll();
    }

    public Section get(int id) {
        return sectionRepository.findById(id)
                                .orElseThrow(() -> new IllegalArgumentException("There's no section with the ID"));
    }

    public List<Section> getByName(String name) {
        return sectionRepository.getSectionsByName(name);
    }
}

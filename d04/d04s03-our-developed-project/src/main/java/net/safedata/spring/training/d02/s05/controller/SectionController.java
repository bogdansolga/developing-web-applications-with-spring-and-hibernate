package net.safedata.spring.training.d02.s05.controller;

import net.safedata.spring.training.d02.s05.model.Section;
import net.safedata.spring.training.d02.s05.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/section")
public class SectionController {

    private final SectionService sectionService;

    @Autowired
    public SectionController(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    @GetMapping("")
    public Iterable<Section> getAll() {
        return sectionService.getAll();
    }

    @GetMapping("/{id}")
    public Section getSection(@PathVariable final int id) {
        return sectionService.get(id);
    }

    @GetMapping("/search")
    public List<Section> getSectionsByName(@RequestParam final String name) {
        return sectionService.getByName(name);
    }
}

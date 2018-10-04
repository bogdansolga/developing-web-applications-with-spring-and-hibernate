package net.safedata.spring.training.d02.s05.controller;

import net.safedata.spring.training.d02.s05.model.Store;
import net.safedata.spring.training.d02.s05.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/store") // de joacă :)
public class StoreController {

    private final StoreService storeService;

    @Autowired
    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping("/all")
    public Iterable<Store> getAll() {
        return storeService.getAll();
    }
}

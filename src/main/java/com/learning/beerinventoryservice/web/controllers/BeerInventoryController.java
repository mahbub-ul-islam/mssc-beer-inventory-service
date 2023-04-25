package com.learning.beerinventoryservice.web.controllers;

import com.learning.beerinventoryservice.repositories.BeerInventoryRepository;
import com.learning.beerinventoryservice.web.mappers.BeerInventoryMapper;
import com.learning.beerinventoryservice.web.model.BeerInventoryDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BeerInventoryController {

    private final BeerInventoryRepository repository;
    private BeerInventoryMapper mapper;

    @GetMapping("api/v1/beer/{beerId}/inventory")
    public List<BeerInventoryDto> listBeersById(@PathVariable UUID beerId) {
        log.debug("Finding Inventory for beerId:" + beerId);

        return repository.findAllByBeerId(beerId)
                .stream()
                .map(mapper::beerInventoryToBeerInventoryDto)
                .collect(Collectors.toList());
    }
}

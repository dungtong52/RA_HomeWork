package com.ra.service;

import com.ra.model.Seed;
import com.ra.repository.SeedRepository;

import java.util.List;

public class SeedService {
    private final SeedRepository seedRepository = new SeedRepository();

    public List<Seed> getAllSeeds() {
        return seedRepository.findAll();
    }
}

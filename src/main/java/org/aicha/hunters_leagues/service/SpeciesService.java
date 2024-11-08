package org.aicha.hunters_leagues.service;

import org.aicha.hunters_leagues.domain.Species;
import org.aicha.hunters_leagues.repository.HuntRepository;
import org.aicha.hunters_leagues.repository.SpeciesRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class SpeciesService {

    private final SpeciesRepository speciesRepository;
    private final HuntRepository huntRepository;

    public SpeciesService(SpeciesRepository speciesRepository, HuntRepository huntRepository) {
        this.speciesRepository = speciesRepository;
        this.huntRepository = huntRepository;
    }


    public Page<Species> findAll(Pageable pageable) {
        return speciesRepository.findAll(pageable);
    }

    public Species save(Species species) {
        return speciesRepository.save(species);
    }


    public Species findById(UUID id) {
        return speciesRepository.findById(id).orElse(null);
    }


    @Transactional
    public void delete(UUID speciesId) {
        Species species = speciesRepository.findById(speciesId)
                .orElseThrow(() -> new RuntimeException("Species not found"));

        huntRepository.deleteBySpeciesId(speciesId);

        speciesRepository.delete(species);
    }

    public Species update(UUID id, Species species) {
        Species existingSpecies = speciesRepository.findById(id).orElseThrow(() -> new RuntimeException("Species not found"));
        existingSpecies.setName(species.getName());
        existingSpecies.setCategory(species.getCategory());
        existingSpecies.setMinimumWeight(species.getMinimumWeight());
        existingSpecies.setDifficulty(species.getDifficulty());
        existingSpecies.setPoints(species.getPoints());
        return speciesRepository.save(existingSpecies);
    }
}
package org.aicha.hunters_leagues.web.rest;

import org.aicha.hunters_leagues.mapper.SpeciesMapper;
import org.aicha.hunters_leagues.service.SpeciesService;
import org.aicha.hunters_leagues.web.vm.SpeciesVM;
import org.aicha.hunters_leagues.domain.Species;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/species")
public class SpeciesController {
    private final SpeciesService speciesService;
    private final SpeciesMapper speciesMapper;

    public SpeciesController(SpeciesService speciesService, SpeciesMapper speciesMapper) {
        this.speciesService = speciesService;
        this.speciesMapper = speciesMapper;
    }

    @PostMapping("/save")
    public ResponseEntity<SpeciesVM> save(@Valid @RequestBody SpeciesVM speciesVM) {
        Species species = speciesMapper.toSpecies(speciesVM);
        Species savedSpecies = speciesService.save(species);
        SpeciesVM responseVM = speciesMapper.toVM(savedSpecies);
        return ResponseEntity.ok(responseVM);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpeciesVM> findById(@PathVariable UUID id) {
        Species species = speciesService.findById(id);
        SpeciesVM responseVM = speciesMapper.toVM(species);
        return ResponseEntity.ok(responseVM);
    }

    @GetMapping("/all")
    public ResponseEntity<Page<SpeciesVM>> findAll(Pageable pageable) {
        Page<Species> species = speciesService.findAll(pageable);
        Page<SpeciesVM> responseVMS = species.map(speciesMapper::toVM);
        return ResponseEntity.ok(responseVMS);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SpeciesVM> update(@PathVariable UUID id, @Valid @RequestBody SpeciesVM speciesVM) {
        Species species = speciesMapper.toSpecies(speciesVM);
        Species updatedSpecies = speciesService.update(id, species);
        SpeciesVM responseVM = speciesMapper.toVM(updatedSpecies);
        return ResponseEntity.ok(responseVM);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        speciesService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
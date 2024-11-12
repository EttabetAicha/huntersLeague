package org.aicha.hunters_leagues.web.rest;

import org.aicha.hunters_leagues.domain.Hunt;
import org.aicha.hunters_leagues.repository.dto.HuntDTO;
import org.aicha.hunters_leagues.service.HuntService;
import org.aicha.hunters_leagues.mapper.HuntMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/hunts")
public class HuntController {

    private final HuntService huntService;
    private final HuntMapper huntMapper;

    public HuntController(HuntService huntService, HuntMapper huntMapper) {
        this.huntService = huntService;
        this.huntMapper = huntMapper;
    }

    @PostMapping("/save")
    public ResponseEntity<HuntDTO> save(@RequestBody HuntDTO huntDTO) {
        Hunt hunt = huntMapper.toEntity(huntDTO);
        Hunt savedHunt = huntService.save(hunt);
        return ResponseEntity.ok(huntMapper.toDTO(savedHunt));
    }

    @GetMapping("/all")
    public ResponseEntity<List<HuntDTO>> findAll() {
        List<Hunt> hunts = huntService.findAll();
        return ResponseEntity.ok(hunts.stream().map(huntMapper::toDTO).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HuntDTO> findById(@PathVariable UUID id) {
        Hunt hunt = huntService.findById(id);
        return ResponseEntity.ok(huntMapper.toDTO(hunt));
    }

    @PostMapping("/update-scores")
    public ResponseEntity<Void> updateHuntScores() {
        huntService.updateHuntScores();
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        huntService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
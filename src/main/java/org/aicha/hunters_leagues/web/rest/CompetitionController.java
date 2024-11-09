package org.aicha.hunters_leagues.web.rest;

import jakarta.validation.Valid;
import org.aicha.hunters_leagues.domain.Competition;
import org.aicha.hunters_leagues.mapper.CompetitionMapper;
import org.aicha.hunters_leagues.repository.dto.CompetitionDetailsDTO;
import org.aicha.hunters_leagues.service.CompetitionService;

import org.aicha.hunters_leagues.web.vm.CompetitionVM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/competition")
public class CompetitionController {

    private final CompetitionService competitionService;
    private final CompetitionMapper competitionMapper;

    public CompetitionController(CompetitionService competitionService, CompetitionMapper competitionMapper) {
        this.competitionService = competitionService;
        this.competitionMapper = competitionMapper;
    }

    @PostMapping("/save")
    public ResponseEntity<CompetitionVM> save(@RequestBody @Valid CompetitionVM competitionVm) {
        Competition competition = competitionMapper.toCompetition(competitionVm);
        Competition savedCompetition = competitionService.save(competition);
        CompetitionVM responseVM = competitionMapper.toVM(savedCompetition);
        return new ResponseEntity<>(responseVM, HttpStatus.CREATED);
    }

    @GetMapping("/competitions")
    public ResponseEntity<Page<CompetitionVM>> findAll(Pageable pageable) {
        Page<Competition> competitions = competitionService.findAll(pageable);
        Page<CompetitionVM> competitionVMS = competitions.map(competitionMapper::toVM);
        return new ResponseEntity<>(competitionVMS, HttpStatus.OK);
    }

    @GetMapping("/{id}/details")
    public ResponseEntity<CompetitionVM> competitionDetails(@PathVariable UUID id) {
        CompetitionDetailsDTO competitionDetailsDTO = competitionService.competitionDetails(id);
        CompetitionVM competitionVM = competitionMapper.toDetailsVM(competitionDetailsDTO);
        return new ResponseEntity<>(competitionVM, HttpStatus.OK);
    }
}
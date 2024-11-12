package org.aicha.hunters_leagues.web.rest;

import jakarta.validation.Valid;
import org.aicha.hunters_leagues.repository.dto.ScoreDTO;
import org.aicha.hunters_leagues.service.ParticipationService;
import org.aicha.hunters_leagues.web.vm.ParticipationVM;
import org.aicha.hunters_leagues.mapper.ParticipationMapper;
import org.aicha.hunters_leagues.domain.Participation;
import org.aicha.hunters_leagues.repository.dto.CompetitionResultDTO;
import org.aicha.hunters_leagues.repository.dto.PodiumDTO;
import org.aicha.hunters_leagues.repository.dto.CompetitionHistoryDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/participations")
public class ParticipationController {

    private final ParticipationService participationService;
    private final ParticipationMapper participationMapper;

    public ParticipationController(ParticipationService participationService, ParticipationMapper participationMapper) {
        this.participationService = participationService;
        this.participationMapper = participationMapper;
    }

    @PostMapping("/save")
    public ResponseEntity<ParticipationVM> save(@Valid @RequestBody ParticipationVM participationVM) {
        Participation participation = participationMapper.toEntity(participationVM);
        Participation savedParticipation = participationService.save(participation);
        ParticipationVM participationVM1 = participationMapper.toVM(savedParticipation);
        return ResponseEntity.ok(participationVM1);
    }

    @GetMapping("/all")
    public ResponseEntity<Page<ParticipationVM>> findAll(Pageable pageable) {
        Page<Participation> participations = participationService.findAll(pageable);
        Page<ParticipationVM> participationVM = participations.map(participationMapper::toVM);
        return ResponseEntity.ok(participationVM);
    }

    @GetMapping("/{userId}/results")
    public List<CompetitionResultDTO> getUserCompetitionResults(@PathVariable UUID userId) {
        return participationService.getUserCompetitionResults(userId);
    }

    @GetMapping("/{competitionId}/podium")
    public List<PodiumDTO> getCompetitionPodium(@PathVariable UUID competitionId) {
        return participationService.getCompetitionPodium(competitionId);
    }

    @GetMapping("/{appUserId}/competition-history")
    public List<CompetitionHistoryDTO> getAppUserCompetitionHistory(@PathVariable UUID appUserId) {
        return participationService.getUserCompetitionHistory(appUserId);
    }
}
package org.aicha.hunters_leagues.service;

import org.aicha.hunters_leagues.domain.Participation;
import org.aicha.hunters_leagues.repository.ParticipationRepository;
import org.aicha.hunters_leagues.repository.dto.CompetitionHistoryDTO;
import org.aicha.hunters_leagues.repository.dto.CompetitionResultDTO;
import org.aicha.hunters_leagues.repository.dto.PodiumDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ParticipationService {

    private final ParticipationRepository participationRepository;

    public ParticipationService(ParticipationRepository participationRepository) {
        this.participationRepository = participationRepository;
    }

    @Transactional
    public Participation save(Participation participation) {
        return participationRepository.save(participation);
    }

    @Transactional(readOnly = true)
    public Page<Participation> findAll(Pageable pageable) {
        return participationRepository.findAll(pageable);
    }

    @Transactional
    public Participation updateScore(UUID participationId, Double score) {
        Participation participation = participationRepository.findById(participationId)
                .orElseThrow(() -> new RuntimeException("Participation not found"));
        participation.setScore(score);
        return participationRepository.save(participation);
    }

    @Transactional(readOnly = true)
    public List<CompetitionResultDTO> getUserCompetitionResults(UUID userId) {
        return participationRepository.findByUserId(userId).stream()
                .map(this::toCompetitionResultDTO)
                .collect(Collectors.toList());
    }

    public List<PodiumDTO> getCompetitionPodium(UUID competitionId) {
        return participationRepository.findTopThreeByCompetitionIdOrderByScoreDesc(competitionId).stream()
                .map(this::toPodiumDTO)
                .collect(Collectors.toList());
    }

    public List<CompetitionHistoryDTO> getUserCompetitionHistory(UUID userId) {
        return participationRepository.findPastCompetitionsByUserId(userId).stream()
                .map(this::toCompetitionHistoryDTO)
                .collect(Collectors.toList());
    }

    private CompetitionResultDTO toCompetitionResultDTO(Participation participation) {
        return new CompetitionResultDTO(
                participation.getCompetition().getId(),
                participation.getCompetition().getCode(),
                participation.getCompetition().getLocation(),
                participation.getCompetition().getDate(),
                participation.getScore()
        );
    }

    private PodiumDTO toPodiumDTO(Participation participation) {
        return new PodiumDTO(
                participation.getId(),
                participation.getUser().getUsername(),
                participation.getScore()
        );
    }

    private CompetitionHistoryDTO toCompetitionHistoryDTO(Participation participation) {
        return new CompetitionHistoryDTO(
                participation.getCompetition().getId(),
                participation.getCompetition().getDate(),
                participation.getCompetition().getLocation(),
                participation.getScore(),
                calculateRank(participation)
        );
    }

    private Integer calculateRank(Participation participation) {
        List<Participation> sortedParticipants = participation.getCompetition().getParticipations().stream()
                .sorted((p1, p2) -> Double.compare(p2.getScore(), p1.getScore()))
                .toList();

        return sortedParticipants.stream()
                .map(Participation::getId)
                .toList()
                .indexOf(participation.getId()) + 1;
    }
}
package org.aicha.hunters_leagues.service;

import org.aicha.hunters_leagues.domain.Competition;
import org.aicha.hunters_leagues.repository.CompetitionRepository;
import org.aicha.hunters_leagues.repository.dto.CompetitionDetailsDTO;
import org.aicha.hunters_leagues.web.exception.CompetitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class CompetitionService {

    private final CompetitionRepository competitionRepository;

    public CompetitionService(CompetitionRepository competitionRepository) {
        this.competitionRepository = competitionRepository;
    }

    @Transactional
    public Competition save(Competition competition) {
        Optional<Competition> competitionOptional = competitionRepository.findByCode(competition.getCode());
        if (competitionOptional.isPresent()) {
            throw new NullPointerException("Competition already exists");
        }
        return competitionRepository.save(competition);
    }

    @Transactional(readOnly = true)
    public Page<Competition> findAll(Pageable pageable) {
        return competitionRepository.findAll(pageable);
    }

    public CompetitionDetailsDTO competitionDetails(UUID competitionId) {
        Competition competition = competitionRepository.findById(competitionId)
                .orElseThrow(() -> new CompetitionException("Competition not found"));

        Integer numberOfParticipants = competition.getParticipations() != null ? competition.getParticipations().size() : 0;

        return new CompetitionDetailsDTO(
                competition.getLocation(),
                competition.getDate(),
                numberOfParticipants
        );
    }
}
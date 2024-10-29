package org.aicha.hunters_leagues.repository;

import org.aicha.hunters_leagues.domain.Competition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CompetitionRepository extends JpaRepository<Competition, UUID> {
}
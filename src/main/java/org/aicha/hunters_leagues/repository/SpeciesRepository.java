package org.aicha.hunters_leagues.repository;

import org.aicha.hunters_leagues.domain.Species;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpeciesRepository extends JpaRepository<Species, UUID> {
}
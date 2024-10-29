
package org.aicha.hunters_leagues.repository;

import org.aicha.hunters_leagues.domain.Participation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ParticipationRepository extends JpaRepository<Participation, UUID> {
}
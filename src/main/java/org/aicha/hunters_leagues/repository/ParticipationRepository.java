
package org.aicha.hunters_leagues.repository;

import org.aicha.hunters_leagues.domain.Participation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ParticipationRepository extends JpaRepository<Participation, UUID> {
    List<Participation> findByUserId(UUID userId);
    @Query("SELECT p FROM Participation p WHERE p.competition.id = :competitionId ORDER BY p.score DESC LIMIT 3")
    List<Participation> findTopThreeByCompetitionIdOrderByScoreDesc(@Param("competitionId") UUID competitionId);
    @Query(" SELECT p FROM Participation p JOIN FETCH p.competition c WHERE p.user.id =:appUserId AND c.date<CURRENT_TIMESTAMP ORDER BY c.date DESC ")
    List<Participation> findPastCompetitionsByUserId(@Param("UserId") UUID appUserId);
}
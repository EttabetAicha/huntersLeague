package org.aicha.hunters_leagues.repository;

import org.aicha.hunters_leagues.domain.Hunt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface HuntRepository extends JpaRepository<Hunt, UUID> {
    List<Hunt> findBySpeciesId(UUID speciesId);
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM hunt WHERE species_id = :speciesId", nativeQuery = true)
    void deleteBySpeciesId(@Param("speciesId") UUID speciesId);
    @Procedure("update_hunt_scores")
    void updateHuntScores();
}
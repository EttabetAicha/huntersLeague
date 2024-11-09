package org.aicha.hunters_leagues.repository;

import org.aicha.hunters_leagues.domain.Hunt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HuntRepository extends JpaRepository<Hunt, UUID> {
}
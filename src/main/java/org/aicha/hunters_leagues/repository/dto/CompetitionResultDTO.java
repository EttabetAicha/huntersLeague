package org.aicha.hunters_leagues.repository.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
public class CompetitionResultDTO {
    private UUID competitionId;
    private String competitionCode;
    private String location;
    private LocalDateTime date;
    private Double score;
}
package org.aicha.hunters_leagues.repository.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class HuntDTO {
    private UUID id;
    private UUID speciesId;
    private Double weight;
    private UUID participationId;
}
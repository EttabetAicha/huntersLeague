package org.aicha.hunters_leagues.repository.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.UUID;

@Data
@AllArgsConstructor
public class ScoreDTO {
    private UUID participationId;
    private Double score;

}

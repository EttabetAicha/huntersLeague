package org.aicha.hunters_leagues.web.vm;
import org.aicha.hunters_leagues.domain.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;


@Data
public class ParticipationVM {
    @NotNull(message = "App User ID is required")
    private UUID UserId;
    @NotNull(message = "Competition ID is required")
    private UUID competitionId;
    private double score;
}
package org.aicha.hunters_leagues.web.vm;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.aicha.hunters_leagues.domain.enums.Difficulty;
import org.aicha.hunters_leagues.domain.enums.SpeciesType;

@Data
public class SpeciesVM {
    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Category is required")
    private SpeciesType category;

    @NotNull(message = "Minimum weight is required")
    @Positive(message = "Minimum weight must be positive")
    private Double minimumWeight;

    @NotNull(message = "Difficulty is required")
    private Difficulty difficulty;

    @NotNull(message = "Points are required")
    @Positive(message = "Points must be positive")
    private Integer points;
}

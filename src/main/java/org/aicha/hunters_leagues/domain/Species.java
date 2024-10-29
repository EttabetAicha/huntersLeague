package org.aicha.hunters_leagues.domain;


import jakarta.persistence.*;
import lombok.*;
import org.aicha.hunters_leagues.domain.enums.Difficulty;
import org.aicha.hunters_leagues.domain.enums.SpeciesType;

import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Species {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name;

    @Enumerated(EnumType.STRING)
    private SpeciesType category;

    private Double minimumWeight;

    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;

    private Integer points;

}

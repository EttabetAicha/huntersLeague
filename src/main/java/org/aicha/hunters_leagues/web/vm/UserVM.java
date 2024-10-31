package org.aicha.hunters_leagues.web.vm;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.aicha.hunters_leagues.domain.enums.Role;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Setter
@Getter
public class UserVM {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "Username is mandatory")
    private String username;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Role is mandatory")
    private Role role;

    @NotBlank(message = "First name is mandatory")
    private String firstName;

    @NotBlank(message = "Last name is mandatory")
    private String lastName;

    @NotBlank(message = "CIN is mandatory")
    private String cin;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is mandatory")
    private String email;

    @NotBlank(message = "Nationality is mandatory")
    private String nationality;

    @PastOrPresent(message = "Join date cannot be in the future")
    private LocalDateTime joinDate;

    @FutureOrPresent(message = "License expiration date cannot be in the past")
    private LocalDateTime licenseExpirationDate;


}

package org.aicha.hunters_leagues.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.aicha.hunters_leagues.domain.enums.Role;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "Username is mandatory")
    private String username;

    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$", message = "Password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, and one digit")
    private String password;

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

    @OneToMany(mappedBy = "user")
    private List<Participation> participations;
}
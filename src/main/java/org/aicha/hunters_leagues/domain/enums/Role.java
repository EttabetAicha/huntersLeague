package org.aicha.hunters_leagues.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Role {
    ADMIN, MEMBER, JURY;

    @JsonCreator
    public static Role fromValue(String value) {
        return Role.valueOf(value.toUpperCase());
    }

    @JsonValue
    public String toValue() {
        return this.name();
    }
}
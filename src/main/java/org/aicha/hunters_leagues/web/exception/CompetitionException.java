package org.aicha.hunters_leagues.web.exception;

public class CompetitionException extends RuntimeException {
    public CompetitionException(String message) {
        super(message);
    }

    public CompetitionException(String message, Throwable cause) {
        super(message, cause);
    }
}
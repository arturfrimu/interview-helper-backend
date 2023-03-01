package com.arturfrimu.studies.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public final class ExceptionResponse {
    private final LocalDateTime timestamp;
    private final String message;
    private final String details;
}

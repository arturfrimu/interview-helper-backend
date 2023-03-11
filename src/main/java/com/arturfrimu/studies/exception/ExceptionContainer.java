package com.arturfrimu.studies.exception;

public final class ExceptionContainer {

    public static final class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }
}
package com.arturfrimu.interview.helper.exception;

public final class ExceptionContainer {

    public static final class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }
}
package com.flowdocs.exception;

public class UserException {

    public static class NullUserException extends RuntimeException {
        public NullUserException() {
            super("Льгота не найден");
        }
    }
}

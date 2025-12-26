package com.flowdocs.exception;

public class AuthenticationException {

    public static class ConflictRegistrationException extends RuntimeException {
        public ConflictRegistrationException() {
            super("Пользователь уже существует");
        }
    }

    public static class ConflictAuthException extends RuntimeException {
        public ConflictAuthException() {
            super("Неверный логин или пароль");
        }
    }
}

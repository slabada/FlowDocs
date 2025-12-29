package com.flowdocs.exception;

public class ApprovalException {

    public static class NonExpertException extends RuntimeException {
        public NonExpertException() {
            super("Данный пользователь не указан как эксперт у документа");
        }
    }

    public static class ConflictApprovalException extends RuntimeException {
        public ConflictApprovalException() {
            super("Пользователь поставил решение/утверждение по документу");
        }
    }

    public static class NonApprovalException extends RuntimeException {
        public NonApprovalException() {
            super("Данный пользователь не ставил решение/утверждение по документу");
        }
    }
}

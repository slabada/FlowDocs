package com.flowdocs.exception;

public class DocumentException {

    public static class NullDocumentException extends RuntimeException {
        public NullDocumentException() {
            super("Документ не найден");
        }
    }
}

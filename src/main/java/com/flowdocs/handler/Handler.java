package com.flowdocs.handler;

import com.flowDocs.model.ErrorResponse;
import com.flowdocs.exception.ApprovalException;
import com.flowdocs.exception.AuthenticationException;
import com.flowdocs.exception.DocumentException;
import com.flowdocs.exception.UserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@RestControllerAdvice
public class Handler {

    /**
     * Not Found (40)
     *
     * @param ex исключение
     * @return кастомный объекит исклбчения
     */
    @ExceptionHandler({
            ApprovalException.NonExpertException.class,
    })
    public ResponseEntity<ErrorResponse> handleBadRequest(Exception ex) {
        ErrorResponse error = new ErrorResponse()
                .message(ex.getMessage())
                .timestamp(OffsetDateTime.now(ZoneOffset.UTC))
                .code(HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(error.getCode()).body(error);
    }

    /**
     * Not Found (404)
     *
     * @param ex исключение
     * @return кастомный объекит исклбчения
     */
    @ExceptionHandler({
            UserException.NullUserException.class,
            DocumentException.NullDocumentException.class,
            ApprovalException.NonApprovalException.class
    })
    public ResponseEntity<ErrorResponse> handleNotFound(Exception ex) {
        ErrorResponse error = new ErrorResponse()
                .message(ex.getMessage())
                .timestamp(OffsetDateTime.now(ZoneOffset.UTC))
                .code(HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(error.getCode()).body(error);
    }

    /**
     * Not Found (409)
     *
     * @param ex исключение
     * @return кастомный объекит исклбчения
     */
    @ExceptionHandler({
            AuthenticationException.ConflictRegistrationException.class,
            AuthenticationException.ConflictAuthException.class,
            ApprovalException.ConflictApprovalException.class,
    })
    public ResponseEntity<ErrorResponse> handleConflict(Exception ex) {
        ErrorResponse error = new ErrorResponse()
                .message(ex.getMessage())
                .timestamp(OffsetDateTime.now(ZoneOffset.UTC))
                .code(HttpStatus.CONFLICT.value());
        return ResponseEntity.status(error.getCode()).body(error);
    }
}

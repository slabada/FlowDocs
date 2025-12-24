package com.flowdocs.handler;

import com.flowDocs.model.ErrorResponseDto;
import com.flowdocs.exception.UserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@RestControllerAdvice
public class Handler {

    /**
     * Not Found (403)
     *
     * @param ex исключение
     * @return кастомный объекит исклбчения
     */
    @ExceptionHandler({
            UserException.NullUserException.class
    })
    public ResponseEntity<ErrorResponseDto> handleBadRequest(Exception ex) {
        ErrorResponseDto error = new ErrorResponseDto()
                .message(ex.getMessage())
                .timestamp(OffsetDateTime.from(LocalDateTime.now()))
                .code(HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(error.getCode()).body(error);
    }

}

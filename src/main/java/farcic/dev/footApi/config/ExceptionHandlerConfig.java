package farcic.dev.footApi.config;

import farcic.dev.footApi.dto.response.ErrorResponseDto;
import farcic.dev.footApi.exception.ConflictException;
import farcic.dev.footApi.exception.UserEmailNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionHandlerConfig {

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ErrorResponseDto> conflictExceptionHandler (Exception e){
        ErrorResponseDto error = new ErrorResponseDto(
                e.getMessage(),
                HttpStatus.CONFLICT.value(),
                LocalDateTime.now()
        );
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(error);
    }

    @ExceptionHandler(UserEmailNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> userEmailNotFoundExceptionHandler (Exception e){
        ErrorResponseDto error = new ErrorResponseDto(
                e.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now()
        );
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(error);
    }
}

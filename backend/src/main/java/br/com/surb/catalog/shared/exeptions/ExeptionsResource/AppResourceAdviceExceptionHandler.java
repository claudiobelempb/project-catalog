package br.com.surb.catalog.shared.exeptions.ExeptionsResource;

import br.com.surb.catalog.shared.constants.ExceptionConstants;
import br.com.surb.catalog.shared.exeptions.ExeptionsService.AppDataIntegrityViolationException;
import br.com.surb.catalog.shared.exeptions.ExeptionsService.AppEntityNotFoundException;
import br.com.surb.catalog.shared.exeptions.StandarError;
import br.com.surb.catalog.shared.exeptions.ValidationError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class AppResourceAdviceExceptionHandler {

    @ExceptionHandler(AppResourceNotFondExecption.class)
    public ResponseEntity<StandarError> resourceNotFond(AppResourceNotFondExecption e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        return ResponseEntity.status(status).body(new StandarError(
                Instant.now(),
                status.value(),
                ExceptionConstants.NOT_FOUND,
                e.getMessage(),
                request.getRequestURI())
        );
    }

    @ExceptionHandler(AppEntityNotFoundException.class)
    public ResponseEntity<StandarError> entityNotFound(AppEntityNotFoundException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        return ResponseEntity.status(status).body(new StandarError(
                Instant.now(),
                status.value(),
                ExceptionConstants.ENTITY_NOT_FOUND,
                e.getMessage(),
                request.getRequestURI())
        );
    }

    @ExceptionHandler(AppDataIntegrityViolationException.class)
    public ResponseEntity<StandarError> dataIntegrityViolationException(AppDataIntegrityViolationException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        return ResponseEntity.status(status).body(new StandarError(
                Instant.now(),
                status.value(),
                ExceptionConstants.DATA_INTEGRITY_VIOLATION,
                e.getMessage(),
                request.getRequestURI())
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;

        ValidationError err = new ValidationError();
        err.setTimestamp(Instant.now());
        err.setStatus(status.value());
        err.setError(ExceptionConstants.VIOLATION_EXCEPTION);
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());

        for (FieldError field : e.getBindingResult().getFieldErrors()) {
            err.addErros(field.getField(), field.getDefaultMessage());
        }

        return ResponseEntity.status(status).body(err);
    }
}

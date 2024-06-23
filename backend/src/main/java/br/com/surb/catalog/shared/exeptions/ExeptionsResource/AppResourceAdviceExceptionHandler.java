package br.com.surb.catalog.shared.exeptions.ExeptionsResource;

import br.com.surb.catalog.shared.constants.AppExceptionConstants;
import br.com.surb.catalog.shared.exeptions.ExeptionsService.AppDataIntegrityViolationException;
import br.com.surb.catalog.shared.exeptions.ExeptionsService.AppEntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
                AppExceptionConstants.NOT_FOUND,
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
                AppExceptionConstants.ENTITY_NOT_FOUND,
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
                AppExceptionConstants.DATA_INTEGRITY_VIOLATION,
                e.getMessage(),
                request.getRequestURI())
        );
    }
}

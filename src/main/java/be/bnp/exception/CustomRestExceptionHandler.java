package be.bnp.exception;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler{

    private static final Logger LOG = LoggerFactory.getLogger(CustomRestExceptionHandler.class);

    @ExceptionHandler(IllegalArgumentException.class)
    public final ResponseEntity<Error> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        return ResponseEntity.badRequest().body(errorMessage(HttpStatus.BAD_REQUEST, ex, request));
    }

    private Error errorMessage(HttpStatus httpStatus, IllegalArgumentException ex, WebRequest request) {
       Error error =  Error.builder()
                .status(httpStatus)
                .timestamp(LocalDateTime.now())
                .message(ex.getMessage())
                .path(((ServletWebRequest) request).getRequest().getRequestURI())
                .build();
        LOG.info("{}", error);
        return error;
    }
}
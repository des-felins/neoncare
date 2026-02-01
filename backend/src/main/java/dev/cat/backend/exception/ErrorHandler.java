package dev.cat.backend.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.time.Instant;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ProblemDetail> handleNotFound(
            NotFoundException ex,
            WebRequest request) {

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        problemDetail.setInstance(extractPath(request));
        problemDetail.setProperty("timestamp", Instant.now());
        problemDetail.setProperty("errorCategory", "CLIENT_ERROR");

        return problemResponse(problemDetail);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ProblemDetail> handleValidationExceptions(
            MethodArgumentNotValidException ex,
            WebRequest request) {

        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setInstance(extractPath(request));
        problemDetail.setProperty("timestamp", Instant.now());
        problemDetail.setProperty("errorCategory", "CLIENT_ERROR");

        List<Map<String, String>> errors = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(err -> {
                    assert err.getDefaultMessage() != null;
                    return Map.of(
                            "field", ((FieldError) err).getField(),
                            "message", err.getDefaultMessage()
                    );
                })
                .toList();
        problemDetail.setProperty("errors", errors);

        return problemResponse(problemDetail);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ProblemDetail> handleConstraintValidationExceptions(
            ConstraintViolationException ex,
            WebRequest request) {

        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setInstance(extractPath(request));
        problemDetail.setProperty("timestamp", Instant.now());
        problemDetail.setProperty("errorCategory", "CLIENT_ERROR");

        List<Map<String, String>> violations = ex.getConstraintViolations()
                .stream()
                .map(v -> Map.of(
                        "path", v.getPropertyPath().toString(),
                        "message", v.getMessage()
                ))
                .toList();

        problemDetail.setProperty("violations", violations);

        return problemResponse(problemDetail);
    }

    private URI extractPath(WebRequest request) {
        String desc = request.getDescription(false);
        String uriPart = desc.startsWith("uri=") ? desc.substring(4) : desc;
        return URI.create(uriPart);
    }

    private ResponseEntity<ProblemDetail> problemResponse(ProblemDetail problemDetail) {
        return ResponseEntity
                .status(problemDetail.getStatus())
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(problemDetail);
    }

}

package org.enricogiurin.poc.italymunicipalities.rest.aop;


import lombok.extern.slf4j.Slf4j;
import org.enricogiurin.poc.italymunicipalities.exception.DataNotFoundException;
import org.enricogiurin.poc.italymunicipalities.rest.record.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
@Slf4j
public class RestAdvice {

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<Message> handle404(DataNotFoundException e) {
        log.error(e.getMessage(), e);
        Message message = Message.builder()
                .mgs(e.getMessage())
                .build();
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Message> genericError(Exception e) {
        log.error("Unexpected error", e);
        Message message = Message.builder()
                .mgs("Unexpected error: \n" + e.toString())
                .build();
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}

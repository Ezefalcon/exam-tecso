package com.tecso.exam.handler;

import com.tecso.exam.domain.dto.ErrorDTO;
import com.tecso.exam.service.exceptions.AlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Created by efalcon
 */
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ErrorDTO> invalidSingUpHandler(AlreadyExistsException exc) {
        return generateResponse(exc, HttpStatus.CONFLICT);
    }

    private static ResponseEntity<ErrorDTO> generateResponse(Exception exc, HttpStatus httpStatus) {
        ErrorDTO errorDTO = new ErrorDTO(exc);
        return new ResponseEntity(errorDTO, httpStatus);
    }
}

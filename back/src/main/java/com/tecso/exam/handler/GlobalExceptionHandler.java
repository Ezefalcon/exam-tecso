package com.tecso.exam.handler;

import com.tecso.exam.domain.dto.ErrorDTO;
import com.tecso.exam.service.exceptions.ServiceException;
import com.tecso.exam.service.exceptions.alreadyexists.AlreadyExistsException;
import com.tecso.exam.service.exceptions.notfound.NotFoundException;
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

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> test(Exception exc) {
        return generateResponse(exc, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ErrorDTO> alreadyExistsHandler(AlreadyExistsException exc) {
        return generateResponse(exc, HttpStatus.CONFLICT);
    }

    @ExceptionHandler({NotFoundException.class, ServiceException.class})
    public ResponseEntity<ErrorDTO> notFoundHandler(Exception exc) {
        return generateResponse(exc, HttpStatus.BAD_REQUEST);
    }

    private static ResponseEntity<ErrorDTO> generateResponse(Exception exc, HttpStatus httpStatus) {
        ErrorDTO errorDTO = new ErrorDTO(exc);
        return new ResponseEntity(errorDTO, httpStatus);
    }
}

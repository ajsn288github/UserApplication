package com.pnc.training.userapp.exception;

import com.pnc.training.userapp.constants.Constants;
import com.pnc.training.userapp.model.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PL64640 on 9/24/2017.
 */
@ControllerAdvice
public class UserException {

    private enum status {
        SUCCESS, FAIL, ERROR
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDto> invalidInput(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        ErrorDto response = new ErrorDto();
        response.setErrorCode(HttpStatus.BAD_REQUEST.value());
        response.setErrorMessage(fromBindingErrors(result));
        return new ResponseEntity<ErrorDto>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorDto> requestHandlingNoHandlerFound(HttpRequestMethodNotSupportedException ex) {
        ErrorDto response = new ErrorDto();
        response.setErrorCode(HttpStatus.BAD_REQUEST.value());
        response.setErrorMessage(Constants.METHOD_NOT_SUPPORTED);
        return new ResponseEntity<ErrorDto>(response, HttpStatus.BAD_REQUEST);
    }

    private List<String> fromBindingErrors(Errors errors) {
        List<String> validErrors = new ArrayList<String>();
        for (ObjectError objectError : errors.getAllErrors()) {
            validErrors.add(objectError.getDefaultMessage());
        }
        return validErrors;
    }
}

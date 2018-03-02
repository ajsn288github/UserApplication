package com.pnc.training.userapp.controller;

import com.pnc.training.userapp.constants.Constants;
import com.pnc.training.userapp.model.dto.ErrorDto;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/error")
public class UserErrorController implements ErrorController {

    @RequestMapping
    public ResponseEntity<ErrorDto> error(HttpServletRequest aRequest){
        ErrorDto response = new ErrorDto();
        response.setErrorCode(HttpStatus.BAD_REQUEST.value());
        response.setErrorMessage(Constants.BAD_URL_EXCECTION_MESSAGE);
        return new ResponseEntity<ErrorDto>(response, HttpStatus.BAD_REQUEST);
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
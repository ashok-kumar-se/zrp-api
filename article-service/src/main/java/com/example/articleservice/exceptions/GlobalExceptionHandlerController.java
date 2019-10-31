package com.example.articleservice.exceptions;

import com.example.articleservice.exceptions.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestControllerAdvice
public class GlobalExceptionHandlerController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(CustomException.class)
    public final ResponseEntity handleCustomException(HttpServletRequest res, CustomException ex) {
        Response response = new Response();
        response.setStatus(ex.getHttpStatus());
        response.addErrorMsgToResponse(ex.getMessage(), ex);
        return new ResponseEntity(response, ex.getHttpStatus());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public final ResponseEntity handleHttpRequestMethodNotSupportedException(HttpServletResponse res, HttpRequestMethodNotSupportedException ex) throws IOException {
        Response response = Response.methodNotSupported();
        response.addErrorMsgToResponse(ex.getMessage(), ex);
        return new ResponseEntity(response, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public final ResponseEntity handleIllegalArgumentException(HttpServletResponse res, IllegalArgumentException ex) throws IOException {
        Response response = Response.badRequest();
        response.addErrorMsgToResponse(ex.getMessage(), ex);
        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity handleException(HttpServletResponse res, Exception ex) throws IOException {
        Response response = Response.badRequest();
        response.addErrorMsgToResponse(ex.getMessage(), ex);
        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }

}

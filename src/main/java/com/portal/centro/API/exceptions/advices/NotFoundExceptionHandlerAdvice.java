package com.portal.centro.API.exceptions.advices;

import com.portal.centro.API.exceptions.NotFoundException;
import com.portal.centro.API.model.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class NotFoundExceptionHandlerAdvice {

    @ExceptionHandler({NotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private ApiError handlerGenericExceptionError(
            Exception exception,
            HttpServletRequest request) {

        return new ApiError(HttpStatus.NOT_FOUND.value(), exception.getMessage(), request.getServletPath());
    }

}

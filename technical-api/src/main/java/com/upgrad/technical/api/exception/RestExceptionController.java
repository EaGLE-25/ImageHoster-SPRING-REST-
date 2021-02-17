package com.upgrad.technical.api.exception;


import com.upgrad.technical.api.model.ErrorResponse;
import com.upgrad.technical.service.exception.AuthenticationFailedException;
import com.upgrad.technical.service.exception.ResourceNotFoundException;
import com.upgrad.technical.service.exception.UnauthorizedException;
import com.upgrad.technical.service.exception.UploadFailedException;
import io.swagger.models.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class RestExceptionController {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse>resourceNotFound(ResourceNotFoundException exception, WebRequest request){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.code(exception.getCode());
        errorResponse.message(exception.getErrorMessage());

        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AuthenticationFailedException.class)
    public ResponseEntity<ErrorResponse>autheticationFailed(AuthenticationFailedException exception, WebRequest request){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.code(exception.getCode());
        errorResponse.message(exception.getErrorMessage());

        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UploadFailedException.class)
    public ResponseEntity<ErrorResponse> uploadFailed(UploadFailedException exception,WebRequest request){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.code(exception.getCode());
        errorResponse.message(exception.getErrorMessage());

        return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorResponse> unAuthorized(UnauthorizedException exception,WebRequest request){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.code(exception.getCode());
        errorResponse.message(exception.getErrorMessage());

        return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.UNAUTHORIZED);
    }
}

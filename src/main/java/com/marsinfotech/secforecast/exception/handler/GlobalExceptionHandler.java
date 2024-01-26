package com.marsinfotech.secforecast.exception.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler({ InvalidBearerTokenException.class, AuthenticationException.class, AccessDeniedException.class})
    public ResponseEntity<Object> handleAuthTokenException(AuthenticationException ex, WebRequest request) {
        LOGGER.error("Invalid or Expired JWT Auth Token, error={}, request={}", ex, request);
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("details", "Invalid or expired access token, Please make sure to supply a valid and non expired access token");
        errorMap.put("statusCode", HttpStatus.UNAUTHORIZED.toString());
        return new ResponseEntity<Object>(errorMap, new HttpHeaders(), HttpStatus.UNAUTHORIZED);
    }

//    @ExceptionHandler({ AccessDeniedException.class})
//    public ResponseEntity<Object> handleAccessDeniedException(AuthenticationException ex, WebRequest request) {
//        LOGGER.error("You don't have permission to access this resource, error={}, request={}", ex, request);
//        Map<String, String> errorMap = new HashMap<>();
//        errorMap.put("details", "You don't have permission to access this resource, Please contact support@mars-infotech.com");
//        errorMap.put("statusCode", HttpStatus.FORBIDDEN.toString());
//        return new ResponseEntity<Object>(errorMap, new HttpHeaders(), HttpStatus.FORBIDDEN);
//    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> handleRuntimeException(RuntimeException ex, WebRequest request) {
        LOGGER.error("Unexpected RuntimeException occurred, error={}, request={}", ex, request);
        return new ResponseEntity<Object>("Unexpected RuntimeException occurred", new HttpHeaders(),
                HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> handleAllUncaughtException(Exception ex, WebRequest request) {
        LOGGER.error("Unexpected Exception occurred, error={}, request={}", ex, request);
        return new ResponseEntity<Object>("Unexpected Exception occurred", new HttpHeaders(),
                HttpStatus.FORBIDDEN);
    }
}
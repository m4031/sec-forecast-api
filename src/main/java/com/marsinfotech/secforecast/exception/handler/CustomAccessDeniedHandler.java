package com.marsinfotech.secforecast.exception.handler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import java.io.IOException;

@ControllerAdvice
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomAccessDeniedHandler.class);

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException ex) {
        // Customize the access denied handling logic
        try {
            LOGGER.error("You don't have permission to access this resource, error={}, request={}", ex, request);
//            Map<String, String> errorMap = new HashMap<>();
//            errorMap.put("details", "You don't have permission to access this resource, Please contact support@mars-infotech.com");
//            errorMap.put("statusCode", HttpStatus.FORBIDDEN.toString());
//            return new ResponseEntity<Object>(errorMap, new HttpHeaders(), HttpStatus.FORBIDDEN);
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "You don't have permission to access this resource, Please contact support@mars-infotech.com");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


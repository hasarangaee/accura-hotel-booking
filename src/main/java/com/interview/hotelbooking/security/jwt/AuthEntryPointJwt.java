package com.interview.hotelbooking.security.jwt;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {
    private static final Logger logger = LoggerFactory.getLogger(AuthEntryPointJwt.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        logger.error("Entry Point Error: {}", authException.getMessage());

        int statusCode = response.getStatus();
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        logger.error("statusCode: {}", statusCode);

        switch (statusCode) {
            case HttpServletResponse.SC_FORBIDDEN:
                writeErrorResponse(response, HttpServletResponse.SC_FORBIDDEN, "Access Denied");
                break;

            case HttpServletResponse.SC_NOT_FOUND:
                writeErrorResponse(response, HttpServletResponse.SC_NOT_FOUND, "Not Found");
                break;

            case HttpServletResponse.SC_INTERNAL_SERVER_ERROR:
                writeErrorResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal Server Error");
                break;

            case HttpServletResponse.SC_BAD_REQUEST:
                writeErrorResponse(response, HttpServletResponse.SC_BAD_REQUEST, "Bad Request");
                break;

            case HttpServletResponse.SC_METHOD_NOT_ALLOWED:
                writeErrorResponse(response, HttpServletResponse.SC_METHOD_NOT_ALLOWED, "Method Not Allowed");
                break;

            case HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE:
                writeErrorResponse(response, HttpServletResponse.SC_METHOD_NOT_ALLOWED, "Unsupported Media Type.");
                break;

            default:
                writeErrorResponse(response, HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized: The username or password is incorrect");
        }
    }

    private void writeErrorResponse(HttpServletResponse response, int statusCode, String message) throws IOException {
        response.setStatus(statusCode);
        response.getWriter().write(message);
    }
}

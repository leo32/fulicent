package com.fulicent.common.exception;


import com.fulicent.common.entity.MessageInfo;
import com.fulicent.common.entity.ApiResponseBody;
import com.fulicent.common.entity.ApiResponseStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    private static Logger logger = LoggerFactory.getLogger(ApiExceptionHandler.class);

    @ExceptionHandler(ApiException.class)
    public final ResponseEntity<ApiResponseBody> handleApiResponseException(ApiException ex) {
        return new ResponseEntity<>(
                ApiResponseBody.builder()
                        .status(new MessageInfo(ex.getResponseStatus(), ex.getMessage()))
                        .build(),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ApiResponseBody> handleUnExceptedExceptions(Exception ex) {
        logger.error(ex.getMessage(), ex);
        return new ResponseEntity<>(
                ApiResponseBody.builder()
                        .status(new MessageInfo(ApiResponseStatus.SYSTEM_INTERNAL_ERROR))
                        .build(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

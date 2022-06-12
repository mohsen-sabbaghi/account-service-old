//package com.example.accountservice.exception;
//
//import lombok.extern.slf4j.Slf4j;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.context.request.WebRequest;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//
///**
// * @author m-sabbaghi
// * <a href="https://www.linkedin.com/in/sabbaghi/">...</a>
// * @version 6/11/2022
// */
//@ControllerAdvice
//@Slf4j
//public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
//    private final Logger logger = LoggerFactory.getLogger(RestResponseEntityExceptionHandler.class);
//
//    @ExceptionHandler(value = {IllegalArgumentException.class, IllegalStateException.class})
//    protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
//        String bodyOfResponse = "This should be application specific";
//        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
//    }
//
//    @ExceptionHandler(value = {CustomerNotFoundException.class, AccountNotFoundException.class})
//    public ResponseEntity<Object> handleCustomerNotFoundException(RuntimeException ex, WebRequest request) {
//        logger.debug(ex.getClass().getSimpleName() + " Not FoundException");
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("Content-Type", request.getHeader("Content-Type"));
//        return handleExceptionInternal(ex, ex.getClass().getSimpleName(), headers, HttpStatus.NOT_FOUND, request);
//    }
//
////    @ExceptionHandler(CustomerNotFoundException.class)
////    public ResponseEntity<String> handleCustomerNotFoundException(RuntimeException e) {
////        return handleExceptionBody(e);
////    }
////
////    @ExceptionHandler(BadRequestException.class)
////    public ResponseEntity<String> handleBadRequestException(RuntimeException e) {
////        return handleExceptionBody(e);
////    }
////
////    private ResponseEntity<String> handleExceptionBody(RuntimeException e) {
////        if (e instanceof AccountNotFoundException || e instanceof CustomerNotFoundException) {
////            String errorMessage = "Account Not Found";
////            logger.error(errorMessage);
////            return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
////        }
////        if (e instanceof BadRequestException) {
////            String errorMessage = "Bad Request";
////            logger.error(errorMessage);
////            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
////        }
////
////        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
////    }
//
//}

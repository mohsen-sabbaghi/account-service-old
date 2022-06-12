//package com.example.accountservice.exception;
//
//import org.springframework.http.HttpHeaders;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * @author m-sabbaghi
// * <a href="https://www.linkedin.com/in/sabbaghi/">...</a>
// * @version 6/11/2022
// */
//@Component
//public class RestResponseStatusExceptionResolver extends AbstractHandlerExceptionResolver {
//    @Override
//    protected ModelAndView doResolveException(
//            HttpServletRequest request,
//            HttpServletResponse response,
//            Object handler,
//            Exception ex) {
//        try {
//            if (ex instanceof IllegalArgumentException) {
//                return handleIllegalArgument((IllegalArgumentException) ex, response, request);
//            }
//
//        } catch (Exception handlerException) {
//            logger.warn("Handling of [" + ex.getClass().getName() + "] resulted in Exception", handlerException);
//        }
//        return null;
//    }
//
//    private ModelAndView
//    handleIllegalArgument(IllegalArgumentException ex, HttpServletResponse response, HttpServletRequest request)
//            throws IOException {
//        response.sendError(HttpServletResponse.SC_CONFLICT);
//        String accept = request.getHeader(HttpHeaders.ACCEPT);
//
//        return new ModelAndView();
//    }
//}

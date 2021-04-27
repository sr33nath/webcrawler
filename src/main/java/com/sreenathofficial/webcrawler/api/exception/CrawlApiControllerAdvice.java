/*
 * Copyright (c) 2021 sreenathofficial.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software
 * and associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.sreenathofficial.webcrawler.api.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * CrawlApiControllerAdvice class hosts all the logic to handle all kinds of exception happens in the CrawlApiController class
 * @see com.sreenathofficial.webcrawler.api.controller.CrawlerApiController
 */
@ControllerAdvice
public class CrawlApiControllerAdvice extends ResponseEntityExceptionHandler {

    /**
     * Return HTTP STATUS 400 and error message in below JSON format if ValidationException is thrown from CrawlerApiController
     *
     * @param ex
     * @param request
     * @return {"timestamp":"2021-04-28T00:54:34.00625","message":"Dummy error message"}
     */
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Object> handleValidationException(
            ValidationException ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    /**
     * Return HTTP STATUS 500 and error message in below JSON format if CrawlApiException is thrown from CrawlerApiController
     *
     * @param ex
     * @param request
     * @return {"timestamp":"2021-04-28T00:54:34.00625","message":"Dummy error message"}
     */
    @ExceptionHandler(CrawlApiException.class)
    public ResponseEntity<Object> handleCrawlApiException(
            CrawlApiException ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Return HTTP STATUS 400 and error message in below JSON format if MethodArgumentNotValidException is thrown from CrawlerApiController
     * ie. any other HTTP METHOD is used in the request other than GET
     *
     * @param ex
     * @param headers
     * @param status
     * @param request
     * @return {"timestamp":"2021-04-28T00:54:34.00625","status":"DUMMY_HTTP_STATUS",errors:["error_1", "error_2"]}
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());

        body.put("errors", errors);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    /**
     * Return HTTP STATUS 400 and error message in below JSON format if MissingServletRequestParameterException is thrown from CrawlerApiController
     * ie. if any mandatory HTTP params are missing in the request
     *
     * @param ex
     * @param headers
     * @param status
     * @param request
     * @return {"timestamp":"2021-04-28T00:54:34.00625","message":"Dummy error message"}
     */
    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    /**
     * Return HTTP STATUS 404 and error message in below JSON format if NoHandlerFoundException is thrown from CrawlerApiController
     * ie. if the url in the HTTP request is not configured to be handled by CrawlerApiController
     *
     * @param ex
     * @param headers
     * @param status
     * @param request
     * @return {"timestamp":"2021-04-28T00:54:34.00625","message":"Dummy error message"}
     */
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(
            NoHandlerFoundException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
}

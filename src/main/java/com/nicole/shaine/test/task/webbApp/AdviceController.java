package com.nicole.shaine.test.task.webbApp;

import com.nicole.shaine.test.task.models.Exceptions.abs.EntityExistException;
import com.nicole.shaine.test.task.models.Exceptions.abs.EntityNonExistException;
import com.nicole.shaine.test.task.models.Exceptions.abs.RequestParamException;
import com.nicole.shaine.test.task.models.dto.response.ErrorResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class AdviceController {

    private static final Logger logger = LogManager.getLogger(AdviceController.class);

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse globalException(Exception exception) {
        String msg = exception.getMessage();
        logger.error(msg, exception);
        return new ErrorResponse(msg);
    }

    @ExceptionHandler({EntityNonExistException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse EntityNonExistException(EntityNonExistException exception) {
        logger.warn("обьект не существует", exception);
        return new ErrorResponse(exception.getMessage());
    }

    @ExceptionHandler({EntityExistException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse EntityExistException(EntityExistException exception) {
        logger.warn("обьект уже существует", exception);
        return new ErrorResponse(exception.getMessage());
    }

    @ExceptionHandler({RequestParamException.class, HttpMessageNotReadableException.class, MethodArgumentTypeMismatchException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse RequestBodyException(RequestParamException exception) {
        logger.warn("параметр указан некоректно", exception);
        return new ErrorResponse(exception.getMessage());
    }

}

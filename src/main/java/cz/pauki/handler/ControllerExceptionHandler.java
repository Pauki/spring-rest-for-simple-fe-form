package cz.pauki.handler;

import cz.pauki.dto.response.ExceptionResponse;
import cz.pauki.ennumerate.ErrorCodes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ControllerExceptionHandler {
    private final static Logger LOGGER = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @ExceptionHandler({Exception.class,Throwable.class})
    protected ResponseEntity baseException(final Exception e){
        LOGGER.error("Eror baseException", e);

        return returnResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, new ExceptionResponse(ErrorCodes.INTERNAL_EXCEPTION, e.getMessage()));
    }

    @ExceptionHandler({BindException.class, MethodArgumentNotValidException.class})
    protected ResponseEntity constraintException(final Exception e){

        LOGGER.debug("Eror constraintException", e);

        List<String> defaultMessages = new ArrayList<>();
        BindingResult bindingResult = e instanceof BindException
                ? ((BindException)e).getBindingResult() : ((MethodArgumentNotValidException)e).getBindingResult();
        for (ObjectError error : bindingResult.getAllErrors()){
            if (error instanceof FieldError){
                defaultMessages.add(((FieldError) error).getField() + "-" + error.getDefaultMessage());
            }
            else if (error != null){
                defaultMessages.add(error.getDefaultMessage());
            }
        }
        return returnResponseEntity(HttpStatus.BAD_REQUEST, new ExceptionResponse(ErrorCodes.BAD_REQUEST, String.join("|",defaultMessages)));
    }

    @ExceptionHandler({ConstraintViolationException.class})
    protected ResponseEntity constraintViolationException(final ConstraintViolationException e){
        LOGGER.debug("Erorr constraintViolationException", e);

        List<String> messages = e.getConstraintViolations().stream()
                .map(constraintViolation -> {
                    String name = null;
                    for (Path.Node node : constraintViolation.getPropertyPath()) {
                        name = node.getName();
                    }
                    return name + " - " + constraintViolation.getMessage();
                }).collect(Collectors.toList());

        return returnResponseEntity(HttpStatus.BAD_REQUEST, new ExceptionResponse(ErrorCodes.BAD_REQUEST, String.join("|", messages)));
    }

    /** Return prepared response entity
     *
     * @param status http status to return
     * @param exceptionResponse body - nullable
     * @return response entity
     */
    private ResponseEntity<Object> returnResponseEntity(final HttpStatus status, final ExceptionResponse exceptionResponse){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<>(exceptionResponse, httpHeaders, status);
    }
}

package cn.sincerity.webservice.config;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;



/**
 * GlobalExceptionHandler
 *
 * @author Ht7_Sincerity
 * @date 2023/4/10
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    // extends ResponseEntityExceptionHandler

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = Exception.class)
    public String exceptionHandler(Exception e) {
        e.printStackTrace();
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public String hibernateValidHandler(Exception ex) {
        FieldError fieldError = ((MethodArgumentNotValidException) ex).getFieldError();
        assert fieldError != null;
        return fieldError.getField() + ": " + fieldError.getDefaultMessage();
    }

//    @Override
//    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        if (ex instanceof MethodArgumentNotValidException) {
//            body = ((MethodArgumentNotValidException) ex).getFieldErrors().stream()
//                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
//                    .collect(Collectors.joining("; "));
//
//        }
//        return super.handleExceptionInternal(ex, body, headers, status, request);
//    }
}

package ru.buzynnikov.notemanager.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.buzynnikov.notemanager.exceptions.NotFoundNoteException;

/*
    Данный класс используем для перехвата исключений по всей программе
 */
@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = NotFoundNoteException.class)
    protected ResponseEntity<Object> handler(RuntimeException e, WebRequest request){
        String bodyOfResponse = "Not found note";
        return handleExceptionInternal(
                e,
                bodyOfResponse,
                new HttpHeaders(),
                HttpStatus.NOT_FOUND,
                request);
    }
}

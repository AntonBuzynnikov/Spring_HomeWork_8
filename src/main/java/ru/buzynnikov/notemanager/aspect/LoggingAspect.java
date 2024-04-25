package ru.buzynnikov.notemanager.aspect;

import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.buzynnikov.notemanager.dto.NoteDTO;

import java.util.logging.Level;
import java.util.logging.Logger;

@Aspect
@Component
public class LoggingAspect {
    private Logger logger = Logger.getLogger(String.valueOf(this.getClass()));

    @Pointcut("@annotation(TrackUserAction)")
    public void callAtNoteService(){}

    @Around("callAtNoteService()")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        if(methodName.equals("saveNote")){
            logger.log(Level.INFO, "Заметка создана");
        }
        if(methodName.equals("updateNote")){
            logger.log(Level.INFO, "Заметка изменена");
        }
        if(methodName.equals("deleteNote")){
            logger.log(Level.WARNING, "Заметка удалена");
        }
        return joinPoint.proceed();
    }
}

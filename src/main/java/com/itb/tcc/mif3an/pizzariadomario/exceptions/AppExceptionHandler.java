package com.itb.tcc.mif3an.pizzariadomario.exceptions;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;

// @RestControllerAdvice : Anotação responsável em "capturar" todos os erros lançados pela aplicação

@RestControllerAdvice
public class AppExceptionHandler {

    private static final ZoneId ZONE_BRASIL = ZoneId.of("America/Sao_Paulo");
    private static final Map<Class<? extends Exception>, HttpStatus> EXCEPTION_STATUS_MAP = new HashMap<>();

    static {

        EXCEPTION_STATUS_MAP.put(BadRequest.class, HttpStatus.BAD_REQUEST);
        EXCEPTION_STATUS_MAP.put(NotFound.class, HttpStatus.NOT_FOUND);
        EXCEPTION_STATUS_MAP.put(Unauthorized.class, HttpStatus.UNAUTHORIZED);
        EXCEPTION_STATUS_MAP.put(Forbidden.class, HttpStatus.FORBIDDEN);
        EXCEPTION_STATUS_MAP.put(AccessDeniedException.class, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleAllExceptions(Exception exception, HttpServletRequest request) {

        // Buscando uma determinada classe de erro no Map, caso não encontre considere como default o erro (Servidor)
        HttpStatus status = EXCEPTION_STATUS_MAP.getOrDefault(exception.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);

        String message = ( exception instanceof Forbidden || exception instanceof AccessDeniedException)
                ? "Você não tem permissão para acessar este recurso."
                : (exception.getLocalizedMessage() != null ? exception.getLocalizedMessage() : exception.toString());

              LocalDateTime now = LocalDateTime.now(ZONE_BRASIL);
              String [] messages = message.split(":");
              ErrorMessage errorMessage = new ErrorMessage(now, messages, status);
              return new ResponseEntity<>(errorMessage, new HttpHeaders(), status);
    }

}

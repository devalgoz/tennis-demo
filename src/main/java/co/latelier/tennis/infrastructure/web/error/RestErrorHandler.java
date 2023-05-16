package co.latelier.tennis.infrastructure.web.error;

import co.latelier.tennis.domain.player.PlayerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestErrorHandler {

    @ExceptionHandler(PlayerNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(PlayerNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}

package co.latelier.tennis.domain.player;

public class PlayerNotFoundException extends TennisExcpetion {

    public PlayerNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public PlayerNotFoundException(String message) {
        super(message);
    }
}

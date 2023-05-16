package co.latelier.tennis.domain.player;

public class TennisExcpetion extends RuntimeException{

    public TennisExcpetion(String message, Throwable cause) {
        super(message, cause);
    }

    public TennisExcpetion(String message) {
        super(message);
    }
}

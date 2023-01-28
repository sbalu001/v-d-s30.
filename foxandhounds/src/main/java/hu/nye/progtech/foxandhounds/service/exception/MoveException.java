package hu.nye.progtech.foxandhounds.service.exception;

/**
 * Custom exception which should get thrown, when there is a problem while moving.
 */

public class MoveException extends RuntimeException {

    public MoveException(String message) {
        super(message);
    }

}

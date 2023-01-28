package hu.nye.progtech.foxandhounds.service.command;

/**
 * Interface for commands which are able to interact with the game.
 */


public interface Command {

    boolean canProcess(String input);

    void process(String input);

}

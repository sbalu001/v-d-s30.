package hu.nye.progtech.foxandhounds.service.command.impl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import hu.nye.progtech.foxandhounds.model.GameState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ExitCommandTest {

    private static final String EXIT_COMMAND = "exit";
    private static final String NOT_EXIT_COMMAND = "not-exit";

    private GameState gameState;

    private ExitCommand underTest;

    @BeforeEach
    public void setUp() {
        gameState = new GameState(null, false);
        underTest = new ExitCommand(gameState);
    }

    @Test
    public void testCanProcessShouldReturnTrueWhenInputIsExit() {
        // given in setup

        // when
        boolean result = underTest.canProcess(EXIT_COMMAND);

        // then
        assertTrue(result);
    }

    @Test
    public void testCanProcessShouldReturnFalseWhenInputIsNotExit() {
        // given in setup

        // when
        boolean result = underTest.canProcess(NOT_EXIT_COMMAND);

        // then
        assertFalse(result);
    }

    @Test
    public void testProcessShouldChangeShouldExitFieldOfGameState() {
        // given in setup

        // when
        underTest.process(EXIT_COMMAND);

        // then
        assertTrue(gameState.isUserWantsToExit());
    }

}

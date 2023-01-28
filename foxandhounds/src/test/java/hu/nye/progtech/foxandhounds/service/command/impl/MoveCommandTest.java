package hu.nye.progtech.foxandhounds.service.command.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoInteractions;

import ch.qos.logback.classic.util.StatusViaSLF4JLoggerFactory;
import hu.nye.progtech.foxandhounds.model.GameState;
import hu.nye.progtech.foxandhounds.model.MapVo;
import hu.nye.progtech.foxandhounds.service.command.performer.MovePerformer;
import hu.nye.progtech.foxandhounds.service.exception.MapReadException;
import hu.nye.progtech.foxandhounds.service.exception.MoveException;
import hu.nye.progtech.foxandhounds.ui.MapPrinter;
import hu.nye.progtech.foxandhounds.ui.PrintWrapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MoveCommandTest {

    private static final String MOVE_COMMAND = "move 6 1 1 1 2";
    private static final String NOT_ENOUGH_PARTS_MOVE_COMMAND = "move 6 1 1 2";
    private static final String NOT_VALID_PARTS_MOVE_COMMAND = "move 6 1 5 1 2";
    private static final String MISSING_MOVE_COMMAND = "6 1 1 1 2";


    private GameState gameState;
    @Mock
    private PrintWrapper printWrapper;

    private MapPrinter mapPrinter;

    private MovePerformer movePerformer;

    private MoveCommand underTest;



    @BeforeEach
    public void setUp() {
        gameState = new GameState(null, false);
        underTest = new MoveCommand( movePerformer, gameState, mapPrinter, printWrapper);
    }

    @Test
    public void testCanProcessShouldReturnTrueWhenInputValid () {
        //given

        //when
        boolean result = underTest.canProcess(MOVE_COMMAND);
        //then
        assertTrue(result);
    }

    @Test
    public void testCanProcessShouldReturnFalseWhenInputHasTooFewParts() {
        //given

        //when
        boolean result = underTest.canProcess(NOT_ENOUGH_PARTS_MOVE_COMMAND);
        //then
        assertFalse(result);
    }

    @Test
    public void testCanProcessShouldReturnFalseWhenInputContainsInvalidValues() {
        //given

        //when
        boolean result = underTest.canProcess(NOT_VALID_PARTS_MOVE_COMMAND);
        //then
        assertFalse(result);
    }
    @Test
    public void testCanProcessShouldReturnFalseWhenMoveWordMissing() {
        //given

        //when
        boolean result = underTest.canProcess(MISSING_MOVE_COMMAND);
        //then
        assertFalse(result);
    }




}

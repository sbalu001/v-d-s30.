package hu.nye.progtech.foxandhounds.service.game;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import hu.nye.progtech.foxandhounds.model.GameState;
import hu.nye.progtech.foxandhounds.model.MapVo;
import hu.nye.progtech.foxandhounds.service.util.MapUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GameControllerTest {

    private static final MapVo MAP_VO = new MapVo(0, 0, null);
    private static final String input = "asd";

    private GameState gameState;
    @Mock
    private GameStepPerformer gameStepPerformer;
    @Mock
    private MapUtil mapUtil;

    private GameController underTest;

    @Test
    public void testStartShouldLoopTheGameUntilTheUserDoesNotForceExit() throws IOException {
        // given
        gameState = new GameState(null, true);
        underTest = new GameController(gameState, mapUtil, gameStepPerformer);

        // when
        underTest.gameLoop();

        // then
        verifyNoInteractions(gameStepPerformer);
    }

    @Test
    public void testStartShouldLoopTheGameUntilTheMapIsNotCompleted() throws IOException {
        // given
        gameState = new GameState(MAP_VO, false);
        underTest = new GameController(gameState, mapUtil, gameStepPerformer);
        given(mapUtil.isMapCompleted(MAP_VO)).willReturn(false, true);
        String input = "asd";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        // when
        underTest.gameLoop();

        // then
        verify(mapUtil, times(4)).isMapCompleted(MAP_VO);
        verify(gameStepPerformer, times(1)).performGameStep();
    }

}

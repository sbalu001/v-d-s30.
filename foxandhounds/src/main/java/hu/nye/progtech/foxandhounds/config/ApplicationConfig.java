package hu.nye.progtech.foxandhounds.config;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import hu.nye.progtech.foxandhounds.Main;
import hu.nye.progtech.foxandhounds.model.GameState;
import hu.nye.progtech.foxandhounds.model.MapVo;
import hu.nye.progtech.foxandhounds.service.command.InputHandler;
import hu.nye.progtech.foxandhounds.service.command.performer.MovePerformer;
import hu.nye.progtech.foxandhounds.service.game.GameController;
import hu.nye.progtech.foxandhounds.service.game.GameStepPerformer;
import hu.nye.progtech.foxandhounds.service.input.UserInputReader;
import hu.nye.progtech.foxandhounds.service.map.MapReaderFacade;
import hu.nye.progtech.foxandhounds.service.map.parser.MapParser;
import hu.nye.progtech.foxandhounds.service.map.validation.impl.MapValidatorComposer;
import hu.nye.progtech.foxandhounds.service.map.validation.reader.MapReader;
import hu.nye.progtech.foxandhounds.service.map.validation.reader.impl.BufferedReaderMapReader;
import hu.nye.progtech.foxandhounds.service.util.MapUtil;
import hu.nye.progtech.foxandhounds.ui.MapPrinter;
import hu.nye.progtech.foxandhounds.ui.PrintWrapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Main configuration.
 */

@Configuration
public class ApplicationConfig {
    @Bean(initMethod = "gameLoop")
    GameController gameController(GameState gameState, GameStepPerformer gameStepPerformer, MapUtil mapUtil) {
        return new GameController(gameState, mapUtil, gameStepPerformer);
    }

    @Bean
    GameState gameState(MapValidatorComposer mapValidatorComposer) {
        InputStream is = Main.class.getClassLoader().getResourceAsStream("map/staticmap.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        MapReader mapReader = new BufferedReaderMapReader(reader);




        MapParser mapParser = new MapParser(8, 8);

        MapReaderFacade mapReaderFacade = new MapReaderFacade(mapReader, mapParser, mapValidatorComposer);
        MapVo mapVo = mapReaderFacade.readMap();
        return new GameState(mapVo, false);
    }

    @Bean
    MapParser mapParser() {
        return new MapParser(8, 8);
    }

    @Bean
    GameStepPerformer gameStepPerformer(UserInputReader userInputReader, InputHandler inputHandler) {
        return new GameStepPerformer(userInputReader, inputHandler);
    }

    @Bean
    UserInputReader userInputReader() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        return new UserInputReader(bufferedReader);
    }

    @Bean
    MapPrinter mapPrinter(MapUtil mapUtil, PrintWrapper printWrapper) {
        return new MapPrinter(8, 8, mapUtil, printWrapper);
    }

    @Bean
    MovePerformer putPerformer() {
        return new MovePerformer();
    }

    @Bean
    PrintWrapper printWrapper() {
        return new PrintWrapper();
    }
}

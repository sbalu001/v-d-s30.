package hu.nye.progtech.foxandhounds.config;

import java.util.List;

import hu.nye.progtech.foxandhounds.model.GameState;
import hu.nye.progtech.foxandhounds.model.MapVo;
import hu.nye.progtech.foxandhounds.service.command.Command;
import hu.nye.progtech.foxandhounds.service.command.InputHandler;
import hu.nye.progtech.foxandhounds.service.command.impl.ExitCommand;
import hu.nye.progtech.foxandhounds.service.command.impl.MoveCommand;
import hu.nye.progtech.foxandhounds.service.command.impl.PrintCommand;
import hu.nye.progtech.foxandhounds.service.command.performer.MovePerformer;
import hu.nye.progtech.foxandhounds.ui.MapPrinter;
import hu.nye.progtech.foxandhounds.ui.PrintWrapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration for commands.
 */
@Configuration
public class CommandConfig {

    @Bean
    InputHandler inputHandler(List<Command> commandList) {
        return new InputHandler(commandList);
    }

    @Bean
    PrintCommand printCommand(GameState gameState, MapPrinter mapPrinter) {
        return new PrintCommand(mapPrinter, gameState);
    }

    @Bean
    ExitCommand exitCommand(GameState gameState) {
        return new ExitCommand(gameState);
    }

    @Bean
    MoveCommand moveCommand(MovePerformer movePerformer, GameState gameState, MapPrinter mapPrinter, PrintWrapper
             printWrapper) {
        return new MoveCommand(movePerformer, gameState, mapPrinter, printWrapper);
    }
}

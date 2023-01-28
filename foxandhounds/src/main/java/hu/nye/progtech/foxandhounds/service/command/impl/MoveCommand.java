package hu.nye.progtech.foxandhounds.service.command.impl;


import java.util.regex.Pattern;

import hu.nye.progtech.foxandhounds.model.GameState;
import hu.nye.progtech.foxandhounds.model.MapVo;
import hu.nye.progtech.foxandhounds.service.command.Command;
import hu.nye.progtech.foxandhounds.service.command.performer.MovePerformer;
import hu.nye.progtech.foxandhounds.service.exception.MoveException;
import hu.nye.progtech.foxandhounds.ui.MapPrinter;
import hu.nye.progtech.foxandhounds.ui.PrintWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The command used to move the fox, select a random hound and randomly move with it, but it's not implemented yet.
 */

public class MoveCommand implements Command {

        private static final Logger LOGGER = LoggerFactory.getLogger(MoveCommand.class);

        private static final String MOVE_COMMAND = "^move [0-8] [0-8] [1-4] [0-8] [0-8]$";

        private final MovePerformer movePerformer;

        private GameState gameState;

        private final MapPrinter mapPrinter;

        private PrintWrapper printWrapper;


        @Override
        public boolean canProcess(String input) {
            return Pattern.matches(MOVE_COMMAND, input);
        }

        public MoveCommand(MovePerformer movePerformer, GameState gameState, MapPrinter mapPrinter, PrintWrapper printWrapper) {
            this.movePerformer = movePerformer;
            this.gameState = gameState;
            this.mapPrinter = mapPrinter;
            this.printWrapper = printWrapper;
        }

    @Override
    public void process(String input) {
        String[] parts = input.split(" ");
        int frowIndex = Integer.parseInt(parts[1]);
        int fcolumnIndex = Integer.parseInt(parts[2]);
        int hound = Integer.parseInt(parts[3]);
        int hrowIndex = Integer.parseInt(parts[4]);
        int hcolumnIndex = Integer.parseInt(parts[5]);

        LOGGER.info("Fox is moving to rowIndex = {}, columnIndex = {}", frowIndex, fcolumnIndex);
        LOGGER.info("hound = {}, is moving to rowIndex = {}, columnIndex = {}", hound, hrowIndex, hcolumnIndex);
        try {
            MapVo newMap = movePerformer.perform(gameState.getMapVo(), frowIndex, fcolumnIndex, hound, hrowIndex, hcolumnIndex);
            gameState.setMapVo(newMap);

            mapPrinter.printMap(newMap);
        } catch (MoveException e) {
            LOGGER.error("Move error", e);
            printWrapper.printLine("Move error:" + input);
        }
    }

}


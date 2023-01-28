package hu.nye.progtech.foxandhounds.service.command.performer;

import java.util.Random;

import hu.nye.progtech.foxandhounds.model.MapVo;
import hu.nye.progtech.foxandhounds.service.exception.MoveException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * MovePerformer entry point.
 */
public class MovePerformer {

        private static final Logger LOGGER = LoggerFactory.getLogger(MovePerformer.class);

        /**
         * Moves map values.
         * A move can only be performed, if there is no hound at
         * the requested position.
         * Picks a random hound and moves it down one tile.
         *
         *
         */
        public MapVo perform(MapVo mapVo, int frowIndex, int fcolumnIndex, int hound, int hrowIndex, int hcolumnIndex)
                throws MoveException {
            LOGGER.info("Smowing.");
            int[][] map = mapVo.getValues();


            for (int k = 0; k < mapVo.getNumberOfRows(); k++) {
                for (int l = 0; l < mapVo.getNumberOfColumns(); l++) {
                    if (map[k][l] == mapVo.getHoundMapValue()) {
                        hound--;
                        if (hound == 0) {


            if (map[frowIndex][fcolumnIndex] == mapVo.getHoundMapValue()
                || map[hrowIndex][hcolumnIndex] == mapVo.getHoundMapValue()
                || map[hrowIndex][hcolumnIndex] == mapVo.getFoxMapValue()) {

                LOGGER.warn("Creatures can't move on top of each other");
                throw new MoveException("Creatures can't move on top of each other");


            } else if ((map[frowIndex + 1][fcolumnIndex + 1] == mapVo.getFoxMapValue()
                    || map[frowIndex + 1][fcolumnIndex - 1] == mapVo.getFoxMapValue()
                    || map[frowIndex - 1][fcolumnIndex - 1] == mapVo.getFoxMapValue()
                    || map[frowIndex - 1][fcolumnIndex + 1] == mapVo.getFoxMapValue())
                    && (map[hrowIndex + 1][hcolumnIndex + 1] == map[k][l]
                    || map[hrowIndex + 1][hcolumnIndex - 1] == map[k][l]
                    || map[hrowIndex - 1][hcolumnIndex - 1] == map[k][l]
                    || map[hrowIndex - 1][hcolumnIndex + 1] == map[k][l])) {
                for (int i = 0; i < mapVo.getNumberOfRows(); i++) {
                    for (int j = 0; j < mapVo.getNumberOfColumns(); j++) {
                        if (map[i][j] == mapVo.getFoxMapValue()) {
                            map[i][j] = mapVo.getTileMapValue();
                            map[frowIndex][fcolumnIndex] = mapVo.getFoxMapValue();
                        }
                    }
                }
                map[k][l] = mapVo.getTileMapValue();
                map[hrowIndex][hcolumnIndex] = mapVo.getHoundMapValue();
            } else {
                LOGGER.warn("Wrong command parameter(s)");
                throw new MoveException("Wrong command parameter(s)");
            }
                        }
                    }
                }
            }


            return new MapVo(mapVo.getNumberOfRows(), mapVo.getNumberOfColumns(), map);
        }

    }




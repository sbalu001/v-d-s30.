package hu.nye.progtech.foxandhounds.service.map.validation.impl;

import hu.nye.progtech.foxandhounds.model.MapVo;
import hu.nye.progtech.foxandhounds.service.exception.MapValidationException;
import hu.nye.progtech.foxandhounds.service.map.validation.MapValidator;

/**
 * Checks the number of tiles and foreign values.
 */
public class NumberValidator  implements MapValidator {

    @Override
    public void validate(MapVo mapVo) {
        int animalCounter = 0;
        int tileCounter = 0;
        int foxMapValue = mapVo.getFoxMapValue();
        int houndMapValue = mapVo.getHoundMapValue();
        int numberOfRows = mapVo.getNumberOfRows();
        int numberOfColumns = mapVo.getNumberOfColumns();
        int[][] values = mapVo.getValues();

        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                if (values[i][j] == houndMapValue) {
                    animalCounter++;
                } else if (values[i][j] == foxMapValue) {
                    animalCounter++;
                } else if (values[i][j] == 0) {
                    tileCounter++;

                }
            }

        }
        if (animalCounter + tileCounter != numberOfRows * numberOfColumns) {
            throw new MapValidationException("Map is incomplete or has foreign value");
        }
    }
}


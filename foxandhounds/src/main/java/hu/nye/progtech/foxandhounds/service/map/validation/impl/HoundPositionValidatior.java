package hu.nye.progtech.foxandhounds.service.map.validation.impl;

import hu.nye.progtech.foxandhounds.model.MapVo;
import hu.nye.progtech.foxandhounds.service.exception.MapValidationException;
import hu.nye.progtech.foxandhounds.service.map.validation.MapValidator;

/**
 * Checks the starting position on the hounds on the map.
 */

public class HoundPositionValidatior implements MapValidator {

    @Override
    public void validate(MapVo mapVo) {
        int numberOfColumns = mapVo.getNumberOfColumns();
        int houndMapValue = mapVo.getHoundMapValue();
        int[][] values = mapVo.getValues();

        for (int i = 1; i < numberOfColumns; i = i + 2) {
            if (values[0][i] != houndMapValue) {
                throw new MapValidationException("Hound starting positions are incorrect, " +
                        "in the first row every second cell should have a hound");
            }
        }


    }
}

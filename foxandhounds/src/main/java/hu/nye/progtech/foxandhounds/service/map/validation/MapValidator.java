package hu.nye.progtech.foxandhounds.service.map.validation;

import hu.nye.progtech.foxandhounds.model.MapVo;
import hu.nye.progtech.foxandhounds.service.exception.MapValidationException;

/**
 * Interfave for validating a map.
 */

public interface MapValidator {

    void validate(MapVo mapVo) throws MapValidationException;

}

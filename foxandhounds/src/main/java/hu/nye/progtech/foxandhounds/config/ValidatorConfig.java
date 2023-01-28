package hu.nye.progtech.foxandhounds.config;

import java.util.List;

import hu.nye.progtech.foxandhounds.service.map.validation.MapValidator;
import hu.nye.progtech.foxandhounds.service.map.validation.impl.FoxCountValidator;
import hu.nye.progtech.foxandhounds.service.map.validation.impl.FoxPositionValidator;
import hu.nye.progtech.foxandhounds.service.map.validation.impl.HoundCountValidator;
import hu.nye.progtech.foxandhounds.service.map.validation.impl.HoundPositionValidatior;
import hu.nye.progtech.foxandhounds.service.map.validation.impl.MapValidatorComposer;
import hu.nye.progtech.foxandhounds.service.map.validation.impl.NumberValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * Configuration for Validators.
 */

@Configuration
public class ValidatorConfig {

    @Bean
    MapValidatorComposer mapValidatorComposer(List<MapValidator> mapValidatorList) {
        return new MapValidatorComposer(mapValidatorList);
    }

    @Bean
    MapValidator foxCountValidator() {
        return new FoxCountValidator();
    }

    @Bean
    MapValidator foxPositionValidator() {
        return new FoxPositionValidator();
    }

    @Bean
    MapValidator houndPositionValidator() {
        return new HoundPositionValidatior();
    }

    @Bean
    MapValidator houndCountValidator() {
        return new HoundCountValidator();
    }

    @Bean
    MapValidator numberValidator() {
        return new NumberValidator();
    }
}

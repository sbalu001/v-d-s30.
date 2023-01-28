package hu.nye.progtech.foxandhounds.config;

import hu.nye.progtech.foxandhounds.service.util.MapUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration for util files.
 */
@Configuration
public class UtilConfig {

        @Bean
        MapUtil mapUtil() {
            return new MapUtil();
        }
    }

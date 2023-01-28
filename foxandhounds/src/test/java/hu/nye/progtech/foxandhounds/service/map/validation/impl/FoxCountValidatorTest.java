package hu.nye.progtech.foxandhounds.service.map.validation.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;

import hu.nye.progtech.foxandhounds.model.MapVo;
import hu.nye.progtech.foxandhounds.service.exception.MapValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FoxCountValidatorTest {

        private static int NUMBER_OF_ROWS = 4;
        private static int NUMBER_OF_COLUMNS = 4;

        private static int[][] VALID_VALUES = {
                {0, 7, 0, 7},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {4, 0, 0, 0},
        };

        private static int[][] INVALID_VALUES = {
                {0, 7, 0, 7},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {4, 0, 4, 0},
        };

        private FoxCountValidator underTest;

        @BeforeEach
        public void setUp() {
            underTest = new FoxCountValidator();
        }

        @Test
        public void testValidateShouldThrownExceptionFailure() {
            //given
            MapVo mapVo = new MapVo(NUMBER_OF_ROWS, NUMBER_OF_COLUMNS, INVALID_VALUES);
            //when+then
            assertThrows(MapValidationException.class, () -> {
                underTest.validate(mapVo);
            });
        }

        @Test
        public void testValidateShouldNotThrowExceptionOnSuccess() {
            //given
            MapVo mapVo = new MapVo(NUMBER_OF_ROWS, NUMBER_OF_COLUMNS, VALID_VALUES);
            //when
            underTest.validate(mapVo);
            //then
        }
}

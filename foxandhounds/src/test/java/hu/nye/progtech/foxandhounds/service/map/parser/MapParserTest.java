package hu.nye.progtech.foxandhounds.service.map.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import hu.nye.progtech.foxandhounds.model.MapVo;
import hu.nye.progtech.foxandhounds.service.exception.MapParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MapParserTest {

    private static final int NUMBER_OF_ROWS = 8;
    private static final int NUMBER_OF_COLUMNS = 8;

    private static final List<String> VALID_RAW_MAP = List.of(
            "07070707",
            "00000000",
            "00000000",
            "00000000",
            "00000000",
            "00000000",
            "00000000",
            "40000000"
    );

    private static final List<String> INVALID_RAW_MAP_FEW_ROWS = List.of(
            "07070707",
            "00000000",
            "00000000",
            "00000000",
            "00000000",
            "00000000",
            "40000000"
    );

    private static final int[][] MAP = {
            {0, 7, 0, 7, 0, 7, 0, 7},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {4, 0, 0, 0, 0, 0, 0, 0}
    };

    private static final MapVo EXPECTED_MAP_VO = new MapVo(NUMBER_OF_ROWS, NUMBER_OF_COLUMNS, MAP);

    private MapParser underTest;

    @BeforeEach
    public void setUp() {
        underTest = new MapParser(NUMBER_OF_ROWS,NUMBER_OF_COLUMNS);
    }

    @Test
    public void testParseShouldReturnNewParseMap() {
        //given

        //when
        MapVo result = underTest.parse(VALID_RAW_MAP);
        //then
        assertEquals(EXPECTED_MAP_VO, result);
    }

    @Test
    public void testParseShouldThrowMapParsingExceptionWhenThereAreNotEnoughRows() throws MapParseException{
        //given

        //when-then
        assertThrows(MapParseException.class, () -> {
            underTest.parse(INVALID_RAW_MAP_FEW_ROWS);
        });
    }

}

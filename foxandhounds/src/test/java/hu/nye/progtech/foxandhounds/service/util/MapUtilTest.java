package hu.nye.progtech.foxandhounds.service.util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import hu.nye.progtech.foxandhounds.model.MapVo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MapUtilTest {

    private static final int NUMBER_OF_ROWS = 8;
    private static final int NUMBER_OF_COLUMNS = 8;
    private static final int[][] MAP = {
            {0, 4, 0, 0, 0, 0, 0, 7},
            {7, 0, 7, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {7, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 7, 0, 0},
            {7, 0, 0, 0, 0, 0, 0, 0},
            {0, 7, 0, 7, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
    };
    private static final int[][] NOT_COMPLETED_MAP = {
            {0, 0, 0, 0, 0, 0, 0, 7},
            {7, 0, 7, 0, 4, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {7, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 7, 0, 0},
            {7, 0, 0, 0, 0, 0, 0, 0},
            {0, 7, 0, 7, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
    };
    private static final MapVo MAP_VO = new MapVo(NUMBER_OF_ROWS, NUMBER_OF_COLUMNS, MAP);
    private static final MapVo NOT_COMPLETED_MAP_VO = new MapVo(NUMBER_OF_ROWS, NUMBER_OF_COLUMNS, NOT_COMPLETED_MAP);

    private static final int FIRST_ROW_INDEX = 0;
    private static final int FIRST_COLUMN_INDEX = 0;

    private static final List<Integer> FIRST_ROW_AS_LIST = List.of(0, 4, 0, 0, 0, 0, 0, 7);
    private static final List<Integer> FIRST_COLUMN_AS_LIST = List.of(0, 7, 0, 7, 0, 7, 0, 0);

    private MapUtil underTest;

    @BeforeEach
    public void setUp() {
        underTest = new MapUtil();
    }

    @Test
    public void testGetRowOfMapShouldReturnTheSelectedRowAsList() {
        // given in setup

        // when
        List<Integer> result = underTest.getRowOfMap(MAP_VO, FIRST_ROW_INDEX);

        // then
        assertEquals(FIRST_ROW_AS_LIST, result);
    }

    @Test
    public void testGetColumnOfMapShouldReturnTheSelectedColumnAsList() {
        // given in setup

        // when
        List<Integer> result = underTest.getColumnOfMap(MAP_VO, FIRST_COLUMN_INDEX);

        // then
        assertEquals(FIRST_COLUMN_AS_LIST, result);
    }


    @Test
    public void testIsMapCompletedShouldReturnTrueWhenFoxIsAtTopRow() {
        // given in setup

        // when
        boolean result = underTest.isMapCompleted(MAP_VO);

        // then
        assertTrue(result);
    }

    @Test
    public void testIsMapCompletedShouldReturnFalseWhenThereAreStillZeroValuesInTheMap() {
        // given in setup

        // when
        boolean result = underTest.isMapCompleted(NOT_COMPLETED_MAP_VO);

        // then
        assertFalse(result);
    }

}

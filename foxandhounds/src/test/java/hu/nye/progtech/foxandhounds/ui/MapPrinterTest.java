package hu.nye.progtech.foxandhounds.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;

import hu.nye.progtech.foxandhounds.model.MapVo;
import hu.nye.progtech.foxandhounds.service.util.MapUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MapPrinterTest {

    private static final int NUMBER_OF_ROWS = 8;
    private static final int NUMBER_OF_COLUMNS = 8;
    private static final int BOX_WIDTH = 8;
    private static final int BOX_HEIGHT = 8;

    private static final int[][] MAP = {
            {0, 7, 0, 7, 0, 7, 0, 7},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {4, 0, 0, 0, 0, 0, 0, 0},

    };

    private static final MapVo MAP_VO = new MapVo(NUMBER_OF_ROWS, NUMBER_OF_COLUMNS, MAP);

    private static final List<List<Integer>> ROWS_AS_LISTS = List.of(
            List.of(0, 7, 0, 7, 0, 7, 0, 7),
            List.of(0, 0, 0, 0, 0, 0, 0, 0),
            List.of(0, 0, 0, 0, 0, 0, 0, 0),
            List.of(0, 0, 0, 0, 0, 0, 0, 0),
            List.of(0, 0, 0, 0, 0, 0, 0, 0),
            List.of(0, 0, 0, 0, 0, 0, 0, 0),
            List.of(0, 0, 0, 0, 0, 0, 0, 0),
            List.of(4, 0, 0, 0, 0, 0, 0, 0)
    );

    private static final List<String> MAP_AS_STRING = List.of(
            "===================================",
            "||   | 7 |   | 7 |   | 7 |   | 7 ||",
            "||   |   |   |   |   |   |   |   ||",
            "||   |   |   |   |   |   |   |   ||",
            "||   |   |   |   |   |   |   |   ||",
            "||   |   |   |   |   |   |   |   ||",
            "||   |   |   |   |   |   |   |   ||",
            "||   |   |   |   |   |   |   |   ||",
            "|| 4 |   |   |   |   |   |   |   ||",
            "==================================="
    );

    @Mock
    private MapUtil mapUtil;
    @Mock
    private PrintWrapper printWrapper;
    @Captor
    private ArgumentCaptor<String> printWrapperArgumentCaptor;

    private MapPrinter underTest;

    @BeforeEach
    public void setUp() {
        underTest = new MapPrinter(BOX_WIDTH, BOX_HEIGHT, mapUtil, printWrapper);
    }

    @Test
    public void testPrintMapShouldDelegateCorrectCallsToPrintWrapper() {
        // given
        for (int i = 0; i < ROWS_AS_LISTS.size(); i++) {
            given(mapUtil.getRowOfMap(MAP_VO, i)).willReturn(ROWS_AS_LISTS.get(i));
        }

        // when
        underTest.printMap(MAP_VO);

        // then
        verify(printWrapper, times(10)).printLine(printWrapperArgumentCaptor.capture());
        assertEquals(MAP_AS_STRING, printWrapperArgumentCaptor.getAllValues());
    }

}

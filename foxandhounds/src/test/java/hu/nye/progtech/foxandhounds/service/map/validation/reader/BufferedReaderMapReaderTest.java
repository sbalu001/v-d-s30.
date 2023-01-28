package hu.nye.progtech.foxandhounds.service.map.validation.reader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import hu.nye.progtech.foxandhounds.service.exception.MapReadException;
import hu.nye.progtech.foxandhounds.service.map.validation.reader.impl.BufferedReaderMapReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BufferedReaderMapReaderTest {

    @Mock
    private BufferedReader bufferedReader;

    private BufferedReaderMapReader underTest;

    @BeforeEach
    public void setUp() {
        underTest = new BufferedReaderMapReader(bufferedReader);
    }

    @Test
    public void testReadShouldReturnCorrectResultWhenCalled() throws IOException, MapReadException{
        //given
        given(bufferedReader.readLine()).willReturn("test", null);
        //when
        List<String> result = underTest.read();
        //then
        List<String> expected = List.of("test");
        assertEquals(expected, result);
    }

    @Test
    public void testReadShouldThrowExceptionInCaseOfFailure() throws IOException {
        //given
        given(bufferedReader.readLine()).willThrow(IOException.class);
        //when + then
        assertThrows(MapReadException.class, () -> {
            underTest.read();
        });
    }
}

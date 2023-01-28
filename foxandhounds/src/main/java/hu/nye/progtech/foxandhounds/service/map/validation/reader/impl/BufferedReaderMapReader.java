package hu.nye.progtech.foxandhounds.service.map.validation.reader.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import hu.nye.progtech.foxandhounds.service.exception.MapReadException;
import hu.nye.progtech.foxandhounds.service.map.validation.reader.MapReader;

/**
 * It reads the raw representation of a map.
 */
public class BufferedReaderMapReader implements MapReader {

    private final BufferedReader reader;

    public BufferedReaderMapReader(BufferedReader reader) {
        this.reader = reader;
    }


    @Override
    public List<String> read() {
        String line;
        List<String> result = new ArrayList<>();

        try {
            while ((line = reader.readLine()) != null) {
                result.add(line);
            }
        } catch (IOException e) {
            throw new MapReadException("Failed to read map");
        }
        return result;
    }
}

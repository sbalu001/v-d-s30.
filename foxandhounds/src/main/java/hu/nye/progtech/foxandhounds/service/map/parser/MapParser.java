package hu.nye.progtech.foxandhounds.service.map.parser;

import java.util.List;

import hu.nye.progtech.foxandhounds.model.MapVo;
import hu.nye.progtech.foxandhounds.service.exception.MapParseException;

/**
 * Parses the copy of a raw map into a MapVo object.
 */

public class MapParser {

    private final int numberOfRows;
    private final int numberOfColumns;

    public MapParser(int numberOfRows, int numberOfColumns) throws MapParseException {
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
    }

    /**
     * Separates the maps' units.
     */
    public MapVo parse(List<String> rawMap) {
        checknumberOfRows(rawMap);
        checknumberOfColumns(rawMap);
        int[][] result = getValues(rawMap);


        return new MapVo(numberOfRows, numberOfColumns, result);
    }

    private int[][] getValues(List<String> rawMap) {
        int[][] result = new int[numberOfRows][numberOfColumns];

        for (int i = 0; i < numberOfRows; i++) {

            String row = rawMap.get(i);
            String[] numbersAsString = row.split("");

            for (int j = 0; j < numberOfColumns; j++) {
                int n = Integer.parseInt(numbersAsString[j]);
                result[i][j] = n;
            }
        }

        return result;
    }

    private void checknumberOfRows(List<String> rawMap) {
        if (rawMap.size() != numberOfRows) {
            throw new MapParseException("Number of rows are incorrect");
        }
    }

    private void checknumberOfColumns(List<String> rawMap) {
        if (rawMap.size() != numberOfColumns) {
            throw new MapParseException("Number of columns are incorrect");
        }
    }


}

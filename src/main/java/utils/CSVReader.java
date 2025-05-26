package utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {

    private CSVReader() {
        throw new IllegalStateException("CSVReader class");
    }
    public static List<String[]> readTestData(String filePath) {
        List<String[]> data = new ArrayList<>();

        try (FileReader reader = new FileReader(filePath);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {

            for (CSVRecord record : csvParser) {
                int size = record.size();
                String[] row = new String[size];
                for (int i = 0; i < size; i++) {
                    row[i] = record.get(i);
                }
                data.add(row);
            }

        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + filePath);
            e.printStackTrace();
        }

        return data;
    }
}

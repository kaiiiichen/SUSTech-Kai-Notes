package util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class OriginalFileUtil implements FileUtil {
    @Override
    public List<String> readFileToList(String filePath) {
        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader reader = new BufferedReader(fileReader);
            String line;
            List<String> readLines = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                readLines.add(line);
            }
            reader.close();
            fileReader.close();
            return readLines;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void writeFileFromList(String filePath, List<String> lines) {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            for (String line : lines
            ) {
                writer.write(line);
                writer.write(System.lineSeparator());
            }
            writer.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

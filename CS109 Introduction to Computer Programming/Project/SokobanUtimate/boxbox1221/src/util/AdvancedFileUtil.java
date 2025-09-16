package util;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class AdvancedFileUtil implements FileUtil {
    @Override
    public List<String> readFileToList(String filePath) {
        try {
            return Files.readAllLines(Path.of(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void writeFileFromList(String filePath, List<String> lines) {
        try {
            Files.write(Path.of(filePath), lines, Charset.defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

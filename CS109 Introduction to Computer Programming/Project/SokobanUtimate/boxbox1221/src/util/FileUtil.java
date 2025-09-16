package util;

import java.util.List;

public interface FileUtil {
    public List<String> readFileToList(String filePath);
    public void writeFileFromList(String filePath, List<String> lines);
}

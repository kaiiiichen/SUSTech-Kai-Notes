package util;

import view.game.GameFrame;
import view.level.LevelFrame;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileChooserDemo extends JFrame {
    // display file contents
    private final JTextArea outputArea;

    public FileChooserDemo() throws IOException {
        super("File Chooser"); // 设置窗口标题为 "Demo"
        outputArea = new JTextArea(); // 创建文本区域用于显示文件内容
        add(new JScrollPane(outputArea)); // 将文本区域放入滚动面板中，以便内容可滚动
//        analyzePath();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    public Path getFileOrDirectory() {
        JFileChooser fileChooser = new JFileChooser(); // 创建文件选择器对象
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES); // 设置文件选择器模式为文件和目录

        int result = fileChooser.showOpenDialog(this); // 显示打开对话框

        if (result == JFileChooser.CANCEL_OPTION) {
            System.exit(1); // 如果用户取消选择，退出程序
        }

        // 返回代表所选文件的Path对象
        return fileChooser.getSelectedFile().toPath();
    }


    public void analyzePath() throws IOException {
        Path path = getFileOrDirectory(); // 获取文件或目录的路径
        if (Files.exists(path)) { // 检查路径是否存在
//            // 收集文件或目录信息
//            StringBuilder builder = new StringBuilder();
//            builder.append(String.format("%s%n", path.getFileName()));
//            builder.append(String.format("%s a directory?%n", Files.isDirectory(path) ? "is" : "is not"));
//            builder.append(String.format("Absolute path: %s%n", path.toAbsolutePath()));
//
//            // 输出目录列表
//            if (Files.isDirectory(path)) {
//                builder.append(String.format("%nDirectory contents:%n"));
//                try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(path)) {
//                    for (Path p : directoryStream) {
//                        builder.append(String.format("%s%n", p));
//                    }
//                }
//            }
//
//            outputArea.setText(builder.toString()); // 显示信息
            LevelFrame.getFrameController().loadGame(String.valueOf(path.getFileName()), this);
        } else {
            JOptionPane.showMessageDialog(this, "The file or directory " + path.getFileName() + " doesn't exist.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }

    }

}

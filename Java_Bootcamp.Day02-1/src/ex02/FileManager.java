package ex02;

import java.io.File;

public class FileManager {
    private String sourcePath;

    public FileManager(String sourcePath) throws IllegalArgumentException {
        File pathDir = new File(sourcePath);
        if (!pathDir.isDirectory()) {
            throw new IllegalArgumentException("path not exists");
        }
        this.sourcePath = sourcePath;
    }

    public String getSourcePath() {
        return sourcePath;
    }


    public void goToPath(String path) {
        if (path.equals("..")) {
            String[] parts = sourcePath.split("/");
            if (parts.length > 2) {
                String tmpSourcePath = sourcePath.substring(0, sourcePath.lastIndexOf("/"));
                File dir = new File(sourcePath);
                if (dir.isDirectory()) {
                    this.sourcePath = tmpSourcePath;
                }
            }
        } else {
            String toPath = sourcePath + "/" + path;
            File dir = new File(toPath);
            if (dir.isDirectory()) {
                this.sourcePath = toPath;
            }
        }
    }

    public void printFilesInCurrentDir() throws IllegalArgumentException {
        File dir = new File(sourcePath);
        if (!dir.isDirectory()) {
            throw new IllegalArgumentException("path not exists");
        }
        for (File item : dir.listFiles()) {
            System.out.println(item.getName() + " " + getFormattedFileSize(item.length()));
        }
    }

    public String getFormattedFileSize(long size) {
        if (size < 1024) {
            return size + " B";
        } else if (size < 1024 * 1024) {
            return String.format("%.2f", (double) size / 1024) + " KB";
        } else if (size < 1024 * 1024 * 1024) {
            return String.format("%.2f", (double) size / (1024 * 1024)) + " MB";
        } else {
            return String.format("%.2f", (double) size / (1024 * 1024 * 1024)) + " GB";
        }
    }
}

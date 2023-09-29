package ex02;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class Program {
    public static void main(String[] args) {
        FileManager fm = new FileManager(getPathFromParameters(args));
        Runnable afm = new AdapterFileManager(fm);
        afm.run();
    }

    public static String getPathFromParameters(String[] args) {
        String[] params = args[0].split("=");
        return params[1];
    }
}

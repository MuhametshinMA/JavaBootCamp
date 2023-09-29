package ex02;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Scanner;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class AdapterFileManager implements Runnable {
    FileManager fm;

    Map<String, Command> commands;

    public AdapterFileManager(FileManager fm) {
        this.fm = fm;
        this.commands = createMenuOptions();
    }

    private Map<String, Command> createMenuOptions() {
        Map<String, Command> commands = new java.util.HashMap<>();
        commands.put("ls", parameters -> {
            try {
                fm.printFilesInCurrentDir();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });

        commands.put("pwd", parameters -> System.out.println(fm.getSourcePath()));
        commands.put("exit", parameters -> System.exit(0));
        commands.put("cd", parameters -> {
            parsePathForCd(parameters[0]);
        });
        commands.put("mv", parameters -> {
            executeMVCommand(parameters);
        });
        return commands;
    }

    private void executeMVCommand(String[] parameters) {
        String sourceFileName = parameters[0];
        String targetDirectoryName = parameters[1];
        File sourceDir = new File(fm.getSourcePath());
        File sourceFile = new File(sourceDir, sourceFileName);
        boolean isFileFound = false;
        for (File item : sourceDir.listFiles()) {
            if (item.getName().equals(sourceFileName)) {
                File targetDir = new File(fm.getSourcePath() + "/" + targetDirectoryName);
                if (targetDir.isDirectory()) {
                    File targetFile = new File(targetDir, sourceFileName);
                    try {
                        Files.move(sourceFile.toPath(), targetFile.toPath(), REPLACE_EXISTING);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                } else {
                    renameFile(parameters);
                }
                isFileFound = true;
                break;
            }
        }
        if (!isFileFound) {
            System.out.println(parameters[0] + " File not found");
        }
    }

    private void renameFile(String[] parameters) {
        Path file = Paths.get(fm.getSourcePath() + "/" + parameters[0]);
        try {
            Files.move(file, file.resolveSibling(parameters[1]), REPLACE_EXISTING);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void parsePathForCd(String path) {
        String[] paths = path.split("/");
        for (String p : paths) {
            fm.goToPath(p);
        }
        System.out.println(fm.getSourcePath());
    }

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        System.out.println(fm.getSourcePath());
        while (true) {
            System.out.print("-> ");
            String optStr = sc.nextLine();
            String[] commandAndParams = optStr.split(" ");
            String commandName = commandAndParams[0];
            String[] parameters = new String[commandAndParams.length - 1];
            System.arraycopy(commandAndParams, 1, parameters, 0, parameters.length);
            Command command = commands.get(commandName);
            if (command != null) {
                command.execute(parameters);
            } else {
                for (Map.Entry<String, Command> entry : commands.entrySet()) {
                    System.out.println(entry.getKey());
                }
            }

        }
    }
}

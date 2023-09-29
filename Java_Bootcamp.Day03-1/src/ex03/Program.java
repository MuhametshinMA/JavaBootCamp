package ex03;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Program {
    public static void main(String[] args) {

        int threadCount = Integer.parseInt(parseParameters(args));
        DownloadManager downloadManager = new DownloadManager("files_urls.txt");

        getDownloadThreads(downloadManager, threadCount);
    }

    public static void getDownloadThreads(DownloadManager downloadManager, int threadCount) {
        List<Thread> threads = new LinkedList<>();
        for (int i = 0; i < threadCount; i++) {
            Thread thread = new ThreadDownloader(downloadManager);
            thread.start();
            threads.add(thread);
        }
    }

    private static String parseParameters(String[] args) {
        String[] parameters = args[0].split("=");
        return parameters[1];
    }
}

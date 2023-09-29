package ex03;

import java.io.IOException;
import java.net.URL;

public class ThreadDownloader extends Thread {
    private DownloadManager downloadManager;

    ThreadDownloader(DownloadManager downloadManager) {
        this.downloadManager = downloadManager;
    }

    @Override
    public void run() {
        DownloadedFile url;
        while ((url = downloadManager.getUrlFromList()) != null) {
            try {
                downloadManager.downloadFile(url.getUrl());
                System.out.println(Thread.currentThread().getName() + " finish download file number " + url.getId());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

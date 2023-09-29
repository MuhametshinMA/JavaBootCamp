package ex03;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class DownloadManager {

    private List<DownloadedFile> urls;
    private Set<DownloadedFile> processedUrls;

    private File downloadsDirectory;

    public DownloadManager(String fileName) {
        this.urls = new LinkedList<>();
        getUrlsFromFile(fileName);
        createDownloadDirectory();

        this.processedUrls = new java.util.HashSet<>();
    }

    public synchronized DownloadedFile getUrlFromList() {
        while (!urls.isEmpty()) {
            DownloadedFile url = urls.remove(0);
            if (!processedUrls.contains(url)) {
                processedUrls.add(url);
                System.out.println(Thread.currentThread().getName() + " start download file number " + url.getId());
                return url;
            }
        }
        return null;
    }

    public void getUrlsFromFile(String fileName) {
        try {
            URL pathToSource = Program.class.getResource(fileName);
            BufferedReader reader = new BufferedReader(new FileReader(pathToSource.getFile()));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] urlLine = line.split(" ");
                URL urlFile = new URL(urlLine[1]);
                this.urls.add(new DownloadedFile(urlLine[0], urlFile));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void downloadFile(URL url) throws IOException {
        String[] fileName = url.toString().split("/");
        String name = fileName[fileName.length - 1];

        InputStream inputStream = url.openStream();


        File file = new File(downloadsDirectory, name);
        Files.copy(inputStream, file.toPath());
    }

    public void createDownloadDirectory() {
        File directory = new File("ex03");

        downloadsDirectory = new File(directory, "downloads");
//        System.out.println(downloadsDirectory.getPath());
        if (!downloadsDirectory.exists()) {
            downloadsDirectory.mkdirs();
        }
    }
}

package ex03;

import java.net.URL;

public class DownloadedFile {
    private String id;
    private URL url;

    public DownloadedFile(String id, URL url) {
        this.id = id;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public URL getUrl() {
        return url;
    }
}

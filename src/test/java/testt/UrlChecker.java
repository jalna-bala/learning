package testt;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UrlChecker {
    // Class to hold URL and its status
    public static class UrlStatus {
        String url;
        String status;

        public UrlStatus(String url, String status) {
            this.url = url;
            this.status = status;
        }

        @Override
        public String toString() {
            return url + " - " + status;
        }
    }

    // Method to save the URL list to a file
    public static void saveUrlListToFile(List<UrlStatus> urlStatusList, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (UrlStatus urlStatus : urlStatusList) {
                writer.write(urlStatus.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Create and populate the URL list
        List<UrlStatus> urlStatusList = createUrlList();
        // File path to store URL statuses (relative to Jenkins workspace)
        String filePath = "url_status_list.txt";
        // Save the list to a file
        saveUrlListToFile(urlStatusList, filePath);
    }

    // Sample createUrlList method
    public static List<UrlStatus> createUrlList() {
        List<UrlStatus> urls = new ArrayList<>();
        urls.add(new UrlStatus("https://github.com", "Reachable"));
        urls.add(new UrlStatus("https://example.com", "Not Reachable"));
        return urls;
    }
}

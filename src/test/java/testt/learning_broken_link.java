package testt;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class learning_broken_link {

    WebDriver driver;
    int brokenLinksCount = 0;
    int workingLinksCount = 0;
    int forbiddenLinksCount = 0;
    int skippingNonHttpCount = 0;
    int blockedByAutomationCount = 0;  // Count for links blocked by automation (e.g., LinkedIn's 999 status)

    @BeforeMethod
    public void setUp() {
        // Set the path to the ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\DELL\\Downloads\\chromedriver-win64\\chromedriver-win64\\Chromedriver.exe");

        // Initialize the WebDriver (Chrome)
        driver = new ChromeDriver();

        // Set implicit wait
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // Navigate to the webpage
        driver.get("https://shardaonline.ac.in");

        // Maximize the browser window
        driver.manage().window().maximize();
    }

    @Test
    public void checkBrokenLinks() {
        // Get all the anchor tags (links) on the page
        List<WebElement> links = driver.findElements(By.tagName("a"));

        // Print total number of links
        System.out.println("Total links found: " + links.size());

        // Check each link's status
        for (WebElement link : links) {
            String url = link.getAttribute("href");

            if (url == null || url.isEmpty()) {
                System.out.println("URL is either not configured or empty");
                continue;
            }

            // Check if the URL starts with "http" or "https"
            if (!url.startsWith("http")) {
                System.out.println("Skipping non-HTTP URL: " + url);
                skippingNonHttpCount++;  // Count non-HTTP URLs
                continue;
            }

            try {
                // Open a connection to the URL and check the response status code
                HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
                connection.setRequestMethod("HEAD");
                connection.connect();

                int responseCode = connection.getResponseCode();

                if (responseCode == 403) {
                    System.out.println(url + " is a forbidden link (status code: 403).");
                    forbiddenLinksCount++;
                } else if (responseCode == 999) {
                    System.out.println(url + " is blocked by automation (status code: 999).");
                    blockedByAutomationCount++;  // Increment count for blocked links
                } else if (responseCode >= 400) {
                    System.out.println(url + " is a broken link with status code: " + responseCode);
                    brokenLinksCount++;
                } else {
                    System.out.println(url + " is a valid link with status code: " + responseCode);
                    workingLinksCount++;
                }

            } catch (IOException e) {
                System.out.println("Exception while checking URL: " + url + " - " + e.getMessage());
                brokenLinksCount++; // Count as broken link if there is an exception
            }
        }

        // Print summary of links
        System.out.println("Total links: " + links.size());
        System.out.println("Total working links: " + workingLinksCount);
        System.out.println("Total broken links: " + brokenLinksCount);
        System.out.println("Total forbidden links: " + forbiddenLinksCount);
        System.out.println("Total skipping non-HTTP URL: " + skippingNonHttpCount);
        System.out.println("Total links blocked by automation: " + blockedByAutomationCount);  
    }

    @AfterMethod
    public void tearDown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }
}

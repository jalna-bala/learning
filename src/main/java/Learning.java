import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Learning {
	 WebDriver driver;

	    @BeforeMethod
	    public void setUp() {
	        System.setProperty("webdriver.chrome.driver", "C:\\Users\\DELL\\Downloads\\chromedriver-win64\\chromedriver-win64\\Chromedriver.exe");
	        driver = new ChromeDriver();
	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	      
	        driver.get("https://www.w3schools.com/");
	        driver.manage().window().maximize();
	    }
	 @Test(priority = 1)
	    public void Method_one() throws InterruptedException {
	        
	   String return_title=    driver.findElement(By.xpath("//a[contains(text(), 'Not Sure Where To Begin?')]")).getText();
	       
	       System.out.println("current is title = "+return_title);
	       
	       String exp_title="Not sure Where To Begin?";
	       
	       if(return_title.equals(exp_title)) {
	    	   
	    	   System.out.println("title correct");
	       }
	       else 
	       {
	    	   System.out.println("title wrong");
	       }
	      
	       driver.close();
	       

	    }
}

package testt;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class learn {
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
	       
	       String exp_title="Not Sure Where To Begin?";
	       
	       if(return_title.equals(exp_title)) {
	    	   
	    	   System.out.println("title correct");
	       }
	       else 
	       {
	    	   System.out.println("title wrong");
	       }
	      
	       driver.close();
	       

	    }
	 @Test(priority = 2)
	    public void Method_two() throws InterruptedException {
		 
		String url= driver.getCurrentUrl();
		System.out.println(url);
	 driver.close();
	 }
	 @Test(priority = 3)
	    public void Method_three() throws InterruptedException {
	        // Set the desired window size
		 driver.manage().window().maximize();
	     
	     Thread.sleep(3000);
	     //                           W & H
	     Dimension Size =new Dimension(400,500);
	     
	     driver.manage().window().setSize(Size);
	     
	     Thread.sleep(5000);
	     
	     System.out.println("resized");
	     driver.quit();
	 }

}

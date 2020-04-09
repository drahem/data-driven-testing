package ddt;


import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
 
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class screenshot {
	
	 public static void main(String[] args) throws IOException {

	    	System.setProperty("webdriver.gecko.driver","C:\\Users\\drahem\\eclipse-workspace\\geckodriver.exe");
	    	WebDriver driver = new FirefoxDriver();


		 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		 driver.manage().window().maximize();
		 driver.get("https://www.lambdatest.com/");
		  
		 File source_file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		 FileUtils.copyFile(source_file, new File("C:\\Users\\drahem\\eclipse-workspace\\screenshot.png"));
		  
		  driver.quit();
		  }


}

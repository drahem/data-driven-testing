package ddt;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class booking extends readdata{
	
	public static String url="https://katalon-demo-cura.herokuapp.com";
	public static WebDriver driver;
	public static String driver_path = "C:\\Users\\drahem\\eclipse-workspace\\geckodriver.exe";
	String CSV_PATH = "C:\\Usedata.csv";
	
	public static WebElement facility;
	public static WebElement tokyo ;
	public static WebElement hogkong ;
	public static WebElement seol ;
	public static WebElement medicare ;
	public static WebElement medicaid ;
	public static WebElement none ;
	public static WebElement readmission ;
	public static WebElement date ;
	public static WebElement comment ;
	public static WebElement book_btn;
	
	
	public static void login() {
		 // go to login page 
		
        //WebElement toggle_menu = driver.findElement(By.id("menu-toggle"));
        //WebElement toggle_menu_login = driver.findElement(By.xpath("//*[@id=\"sidebar-wrapper\"]/ul/li[3]/a"));
        //toggle_menu.click(); 
        //toggle_menu_login.click();
        driver.findElement(By.id("btn-make-appointment")).click();
        /*try {
            Assert.assertEquals( "https://katalon-demo-cura.herokuapp.com/profile.php#login",driver.getCurrentUrl());
        	}
        	catch (AssertionError e) {
        	System.out.println("login failed!!!");
        	}
        	*/
       // access elements of login page 
        WebElement username=driver.findElement(By.id("txt-username"));
        WebElement password=driver.findElement(By.id("txt-password"));
        WebElement login=driver.findElement(By.id("btn-login"));
        
        // enter login data
        username.sendKeys("John Doe");
        password.sendKeys("ThisIsNotAPassword");
        login.click();
        
        String actualUrl=driver.getCurrentUrl();
        String expectedUrl= "https://katalon-demo-cura.herokuapp.com/#appointment";
        
        try {
            Assert.assertEquals(expectedUrl,actualUrl);
        	}
        	catch (AssertionError e) {
        	System.out.println("login failed!!!");
        	}
     
        facility = driver.findElement(By.id("combo_facility"));
		tokyo = driver.findElement(By.xpath("//*[@id=\"combo_facility\"]/option[1]"));
		hogkong = driver.findElement(By.xpath("//*[@id=\"combo_facility\"]/option[2]"));
		seol = driver.findElement(By.xpath("//*[@id=\"combo_facility\"]/option[3]"));
		medicare = driver.findElement(By.id("radio_program_medicare"));
		medicaid = driver.findElement(By.id("radio_program_medicaid"));
		none = driver.findElement(By.id("radio_program_none"));
		readmission = driver.findElement(By.id("chk_hospotal_readmission"));
		date = driver.findElement(By.id("txt_visit_date"));
		comment = driver.findElement(By.id("txt_comment"));
		book_btn = driver.findElement(By.id("btn-book-appointment"));
	}
	
	public static void logout() {
		driver.findElement(By.id("menu-toggle")).click();
		driver.findElement(By.xpath("//*[@id=\"sidebar-wrapper\"]/ul/li[5]/a")).click();
	} 
	@BeforeTest
	public static void setup() {
		
		//driver=new ChromeDriver();
		//System.setProperty("webdriver.chrome.driver","chromedriver.exe");
    	System.setProperty("webdriver.gecko.driver","C:\\Users\\drahem\\eclipse-workspace\\geckodriver.exe");
    	driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get(url);
	}
@AfterTest
	public static void end() {
		driver.quit();
	}
	
	@Test
	public static void make_appointment() throws IOException {
		
		for(int i=1;i<6;i++) {
		System.out.println("test case : "+i);
		setup();
		login();
		String temp0 = ReadCellData(i, 0);
		String temp1 = ReadCellData(i, 1);
		String temp2 = ReadCellData(i, 2);
		String temp3 = ReadCellData(i, 3);
		String temp4 = ReadCellData(i, 4);
	
		// choose facility 
		if(temp0.equals("tokyo")) {
		facility.click();
		tokyo.click();
		}
		if(temp0.equals("hongkong")) {
			facility.click();
			hogkong.click();
			}
		if(temp0.equals("seol")) {
			facility.click();
			seol.click();
		}
			  
			  // read mission 
		if(temp1.equals("check")) {
			readmission.click();
		}	  
			  // choose health care program
	  if(temp2.equals("medicare")) {
			medicaid.click();
	  }
	  else if(temp2.equals("medicaid")){
		  medicaid.click();
	  }
	  else if(temp2.equals("none")) {
		  none.click();
	  }
		
			// enter date 
				date.sendKeys(temp3);	
			// enter comment 
				comment.sendKeys(temp4);
			// click book
				book_btn.click(); 
			
				// take screen shot 
			File source_file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(source_file, new File("screenshot_"+i+".png"));
				driver.close();
		}
		
	}
	
	
}

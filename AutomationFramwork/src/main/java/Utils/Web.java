package Utils;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.NoSuchElementException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import Lib.Files.Base;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Web extends Util {
	WebDriver driver = null;
	WebElement element = null;
	public String xpath="";
	public String display ="";
	private String screenShotPath ="C:/Users/arka.sarkar/Desktop/Data/screenshots/";

	public Web(String browserType) {
		if (browserType.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			maximizeWindow();

		} else if (browserType.equals("edge")) {
			WebDriverManager.edgedriver().setup();
			//driver = new EdgDriver();
		}

	}
	
	public void launchUrl(String url) {
		driver.get(url);
	}
	
	public Web getXP(String xp) {
		String str[] = xp.split("##");
		xpath = str[0];
		display = str[1];
		  try {
			  element =  driver.findElement(By.xpath(xpath));
	        } catch (NoSuchElementException e) {
	            Reporter.info("Element not found:" + e.getMessage());
	            throw e;
	        }
		  return this;
	}
	
	public void highlight() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', 'background-color: rgba(255, 199, 199); border: 0.1px solid red;');", element);
	}
	
	public void maximizeWindow() {
		driver.manage().window().maximize();
	}
	
	public boolean isExist(String xp) {
		 try {
			 String str[] = xp.split("##");
			 xpath = str[0];
			 driver.findElement(By.xpath(xpath));
	            return true;
	        } catch (NoSuchElementException e) {
	            return false;
	        }
	}
	
	 public void screenShot(String fileName) {
	        TakesScreenshot scrShot = ((TakesScreenshot) driver);
	        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
	        try {
	            File destFile = new File(screenShotPath + fileName + ".jpg");
	            FileUtils.copyFile(SrcFile, destFile);
	            Reporter.attachScreenshot(screenShotPath + fileName + ".jpg", display);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	 
	 

	public void setText(String value) {
		highlight();
		element.sendKeys(value);
		Reporter.info("Entered "+display);
		screenShot(display+randomString());
		
	}
	
	public void click() {
		highlight();
		Reporter.info("Clicked "+display);
		screenShot(display+randomString());
		element.click();
		
	}
	
	public String getText(){
		highlight();
		String res =  element.getText();
		Reporter.info("Captured "+ display +" value");
		screenShot(display+randomString());
		return res;
	}
	
	public void quit() {
		driver.quit();
	}
	
}
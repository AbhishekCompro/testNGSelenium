package com.testng.selenium;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestExtractElementsCfg {
	private WebDriver driver;	
	
	static Logger logger = Logger.getLogger(TestExtractElementsCfg.class);
	
	HashMap<String,String> button_map = new HashMap<String, String>();
	HashMap<String,String> a_map = new HashMap<String, String>();
	HashMap<String,String> input_map = new HashMap<String, String>();
	HashMap<String,String> li_map = new HashMap<String, String>();
	
	@Parameters({"browser", "driverPath"})
	@BeforeTest
	public void initDriver(String browser, @Optional("") String driverPath) throws Exception {
		logger.info("You are testing on browser " + browser);
		browser = browser.toLowerCase();
		if (!driverPath.equals("")) {
			System.setProperty("webdriver.chrome.driver", driverPath);
		}
		if (browser.equals("chrome")) {			
			driver = new ChromeDriver();
		} else if (browser.equals("firefox")) {
			driver = new FirefoxDriver();
		} else {
			throw new RuntimeException("Please create a driver for " + browser);
		}
	}
	
	
	@Test
	public void searchTestExtractElements() throws InterruptedException {

	// extract and initialize elements
	init();
	
	// print extracted elements
	printElements();
	
	System.out.println("Starting login test in 15 seconds using extracted xpath..");
	Thread.sleep(15000);
	
/*	System.out.println(input_map.get("login_orgid"));
	System.out.println(button_map.get("login_Signin"));*/
	
	// run test from extractd test
	System.out.println("Test Started...");
	driver.findElement(By.xpath(input_map.get("login_orgid"))).sendKeys("test6");
	driver.findElement(By.xpath(input_map.get("login_username"))).sendKeys("Student1");
	driver.findElement(By.xpath(input_map.get("login_password"))).sendKeys("Compro11");
	Thread.sleep(3000);
	driver.findElement(By.xpath(button_map.get("login_Signin"))).click();
	Thread.sleep(5000);
		
	}
	
	@AfterSuite
	public void quitDriver() throws Exception {
		driver.quit();
	}

	
	
	/**
	 * helper functions 
	 */
	
	//	http://stackoverflow.com/questions/4176560/webdriver-get-elements-xpath 
	public String getElementXPath(WebDriver driver, WebElement element) {
	    return (String)((JavascriptExecutor)driver).executeScript("gPt=function(c){if(c.id!==''){return'id(\"'+c.id+'\")'}if(c===document.body){return c.tagName}var a=0;var e=c.parentNode.childNodes;for(var b=0;b<e.length;b++){var d=e[b];if(d===c){return gPt(c.parentNode)+'/'+c.tagName+'['+(a+1)+']'}if(d.nodeType===1&&d.tagName===c.tagName){a++}}};return gPt(arguments[0]).toLowerCase();", element);
	}
	
	public void printElements(){
		System.out.println("Display <input> elements");
		  /* Display input elements*/
	      Set set = input_map.entrySet();
	      Iterator iterator = set.iterator();
	      while(iterator.hasNext()) {
	         Map.Entry mentry = (Map.Entry)iterator.next();
	         logger.info("key is: "+ mentry.getKey() + " & Value is: " + mentry.getValue());
	      }
	      
	      
	      System.out.println("Display <a> elements");
		  /* Display a elements*/
	      Set set1 = a_map.entrySet();
	      Iterator iterator1 = set1.iterator();
	      while(iterator1.hasNext()) {
	         Map.Entry mentry = (Map.Entry)iterator1.next();
	         logger.info("key is: "+ mentry.getKey() + " & Value is: " + mentry.getValue());
	      }
	      
	      System.out.println("Display <li> elements");
		  /* Display li elements*/
	      Set set2 = li_map.entrySet();
	      Iterator iterator2 = set2.iterator();
	      while(iterator2.hasNext()) {
	         Map.Entry mentry = (Map.Entry)iterator2.next();
	         logger.info("key is: "+ mentry.getKey() + " & Value is: " + mentry.getValue());
	      }
	      
	      System.out.println("Display <button> elements");
		  /* Display button elements*/
	      Set set3 = button_map.entrySet();
	      Iterator iterator3 = set3.iterator();
	      while(iterator3.hasNext()) {
	         Map.Entry mentry = (Map.Entry)iterator3.next();
	         logger.info("key is: "+ mentry.getKey() + " & Value is: " + mentry.getValue());
	      }
		
	}
	
	public void init() throws InterruptedException {
		
		driver.navigate().to("http://reader.comprodls.com/");
		logger.info("Extracting elements for Page: http://www.reader.comprodls.com "+driver.getTitle());

		System.out.println("waiting for 5 sec for page load...");
		Thread.sleep(5000);
		
		extractElements(driver,"login");

		readerLogin(driver);
		System.out.println("waiting for 5 sec for page load...");
		Thread.sleep(5000);
		
		logger.info("Extracting elements for Reader Page after Login");
		extractElements(driver,"dashboard");
		
		driver.findElement(By.xpath("//*[@id='header']/div[2]/ul[2]/li/a")).click();
		driver.findElement(By.xpath("//*[@id='header']/div[2]/ul[2]/li/ul/li[3]/a")).click();
		
//		logger.info("Done.");
	}
	
	public void extractElements(WebDriver driver, String pageName){
		
//		logger.info("########## ########## ##########");
		
		List<WebElement> button_elements = driver.findElements(By.tagName("button"));
		logger.info("Total number of <button> elements: "+button_elements.size());
		for(WebElement el : button_elements) {
            
            String text = el.getText();
//            logger.info(text);
            
            String path = ""; //getElementXPath(driver,el);
            
            path = "//button[. ='"+ el.getText() +"']";
            
            /*logger.info("xpath: "+ path);
            logger.info("");*/
            
            if(text.trim() !="" || text.trim() !=null){
            	button_map.put((pageName + "_" + text.replaceAll(" ", "")), path);	
            }
            
        }
//		logger.info("########## ########## ##########");
		
		List<WebElement> anchor_elements = driver.findElements(By.tagName("a"));
		logger.info("Total number of <a> elements: "+anchor_elements.size());
		for(WebElement el : anchor_elements) {
            
            String text = el.getText();
//            logger.info(text);
            
            String path = ""; //getElementXPath(driver,el);
            
            path = "//a[. ='"+ text +"']";
            
            /*logger.info("xpath: "+ path);
            logger.info("")*/;
            
            text = text.replaceAll("[-+.^:, ]","");
            if(text.trim() !="" || text.trim() !=null){
            a_map.put((pageName + "_" + text.replaceAll(" ", "")), path);
            }
        }
//		logger.info("########## ########## ##########");
		
		List<WebElement> list_elements = driver.findElements(By.tagName("li"));
		logger.info("Total number of <li> elements: "+list_elements.size());
		for(WebElement el : list_elements) {
            
            String text = el.getText();
//            logger.info(text);
            
            String path = ""; //getElementXPath(driver,el);
            
            path = "//li[. ='"+ text +"']";
            
            /*logger.info("xpath: "+ path);
            logger.info("");*/
            
            text = text.replaceAll("[-+.^:, ]","");
            if(text.trim() !="" || text.trim() !=null){
            li_map.put((pageName + "_" + text.replaceAll(" ", "")), path);
            }
        }
//		logger.info("########## ########## ##########");
		
		List<WebElement> input_elements = driver.findElements(By.tagName("input"));
		logger.info("Total number of <input> elements: "+input_elements.size());
		for(WebElement el : input_elements) {
            
            String text = el.getAttribute("name");
//            logger.info(text);
            
            String path = ""; //getElementXPath(driver,el);
            
            path = "//input[@name='"+ text +"']";
            
            /*logger.info("xpath: "+ path);
            logger.info("");*/
            
            text = text.replaceAll("[-+.^:, ]","");
            if(text.trim() !="" || text.trim() !=null){
            input_map.put((pageName + "_" + text.replaceAll(" ", "")), path);
            }
        }
//		logger.info("########## ########## ##########");
	};
	
	public void readerLogin(WebDriver driver) throws InterruptedException{
		
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='login-form']/fieldset/section[1]/label[2]/input")).sendKeys("test6");
		driver.findElement(By.xpath("//*[@id='login-form']/fieldset/section[2]/label[2]/input")).sendKeys("Student1");
		driver.findElement(By.xpath("//*[@id='login-form']/fieldset/section[3]/label[2]/input")).sendKeys("Compro11");
		
		Thread.sleep(3000);
		
		driver.findElement(By.id("submitButton")).click();
	}

}

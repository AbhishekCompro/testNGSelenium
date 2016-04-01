package com.testng.selenium;

import java.util.List;
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
	
	public String getElementXPath(WebDriver driver, WebElement element) {
	    return (String)((JavascriptExecutor)driver).executeScript("gPt=function(c){if(c.id!==''){return'id(\"'+c.id+'\")'}if(c===document.body){return c.tagName}var a=0;var e=c.parentNode.childNodes;for(var b=0;b<e.length;b++){var d=e[b];if(d===c){return gPt(c.parentNode)+'/'+c.tagName+'['+(a+1)+']'}if(d.nodeType===1&&d.tagName===c.tagName){a++}}};return gPt(arguments[0]).toLowerCase();", element);
	}

	@Test
	public void searchTestExtractElements() {
		
		driver.navigate().to("http://www.github.com");
		logger.info("Extracting elements for Page: http://www.github.com "+driver.getTitle());
		logger.info("########## ########## ##########");
		
		List<WebElement> input_elements = driver.findElements(By.tagName("input"));
		logger.info("Total number of <input> elements: "+input_elements.size());
		for(WebElement el : input_elements) {
            logger.info(el.getText());
            String path = getElementXPath(driver,el);
            path = "/html/" + path;
            logger.info("xpath: "+ path);
            logger.info("");
        }
		logger.info("########## ########## ##########");
		
		List<WebElement> anchor_elements = driver.findElements(By.tagName("a"));
		logger.info("Total number of <a> elements: "+anchor_elements.size());
		for(WebElement el : anchor_elements) {
            logger.info(el.getText());
            String path = getElementXPath(driver,el);
            path = "/html/" + path;
            logger.info("xpath: "+ path);
            logger.info("");
        }
		logger.info("########## ########## ##########");
		
		List<WebElement> paragraph_elements = driver.findElements(By.tagName("p"));
		logger.info("Total number of <p> elements: "+paragraph_elements.size());
		for(WebElement el : paragraph_elements) {
            logger.info(el.getText());
            String path = getElementXPath(driver,el);
            path = "/html/" + path;
            logger.info("xpath: "+ path);
            logger.info("");
        }
		logger.info("########## ########## ##########");

		logger.info("Done.");
	}

	@AfterSuite
	public void quitDriver() throws Exception {
		driver.quit();
	}
}

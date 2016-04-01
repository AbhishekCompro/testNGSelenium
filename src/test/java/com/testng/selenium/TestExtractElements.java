package com.testng.selenium;

import java.util.List;

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
import org.testng.annotations.Test;

public class TestExtractElements {
//	private HtmlUnitDriver driver;
	private WebDriver driver;
	
	public String getElementXPath(WebDriver driver, WebElement element) {
	    return (String)((JavascriptExecutor)driver).executeScript("gPt=function(c){if(c.id!==''){return'id(\"'+c.id+'\")'}if(c===document.body){return c.tagName}var a=0;var e=c.parentNode.childNodes;for(var b=0;b<e.length;b++){var d=e[b];if(d===c){return gPt(c.parentNode)+'/'+c.tagName+'['+(a+1)+']'}if(d.nodeType===1&&d.tagName===c.tagName){a++}}};return gPt(arguments[0]).toLowerCase();", element);
	}

	@BeforeSuite
	public void initDriver() throws Exception {
//		driver = new HtmlUnitDriver();
//		driver.setJavascriptEnabled(true);
		
		driver = new ChromeDriver();
		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
	}

	@Test
	public void searchTestExtractElements() {
		driver.navigate().to("http://www.github.com");
		System.out.println(driver.getTitle());
		System.out.println("########## ########## ##########");
		
		List<WebElement> input_elements = driver.findElements(By.tagName("input"));
		System.out.println("Total number of <input> elements: "+input_elements.size());
		for(WebElement el : input_elements) {
            System.out.println(el.getText());
            String path = getElementXPath(driver,el);
            path = "/html/" + path;
            System.out.println(path);
            System.out.println("");
        }
		System.out.println("########## ########## ##########");
		
		List<WebElement> anchor_elements = driver.findElements(By.tagName("a"));
		System.out.println("Total number of <a> elements: "+anchor_elements.size());
		for(WebElement el : anchor_elements) {
            System.out.println(el.getText());
            String path = getElementXPath(driver,el);
            path = "/html/" + path;
            System.out.println(path);
            System.out.println("");
        }
		System.out.println("########## ########## ##########");
		
		List<WebElement> paragraph_elements = driver.findElements(By.tagName("p"));
		System.out.println("Total number of <p> elements: "+paragraph_elements.size());
		for(WebElement el : paragraph_elements) {
            System.out.println(el.getText());
            String path = getElementXPath(driver,el);
            path = "/html/" + path;
            System.out.println(path);
            System.out.println("");
        }
		System.out.println("########## ########## ##########");

		System.out.println("Done.");
	}

	@AfterSuite
	public void quitDriver() throws Exception {
		driver.quit();
	}
}

# testNGSelenium

Sample Project to extract element text and reverse extraction of xpath on basis of elements.

## Getting Started

Download / Clone Project
Navigate to root folder with pom.xml
Run command: mvn test

## Output

output will be displayed on console, Also log file for same will be created in project root folder.

## References: 

http://stackoverflow.com/questions/4176560/webdriver-get-elements-xpath

Core Function Used:

public String getElementXPath(WebDriver driver, WebElement element) {
    return (String)((JavascriptExecutor)driver).executeScript("gPt=function(c){if(c.id!==''){return'id(\"'+c.id+'\")'}if(c===document.body){return c.tagName}var a=0;var e=c.parentNode.childNodes;for(var b=0;b<e.length;b++){var d=e[b];if(d===c){return gPt(c.parentNode)+'/'+c.tagName+'['+(a+1)+']'}if(d.nodeType===1&&d.tagName===c.tagName){a++}}};return gPt(arguments[0]).toLowerCase();", element);
}

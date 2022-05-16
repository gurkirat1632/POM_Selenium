package com.nagarro.utils.web;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.nagarro.config.Configs;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Web_Lib {

    WebDriver driver;

    public Web_Lib(final WebDriver driver) {
        this.driver = driver;
    }

    public static WebDriver launchBrowser(final String strBrowser) {
        WebDriver returnDriver;
        switch (strBrowser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                returnDriver = new ChromeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                returnDriver = new FirefoxDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                returnDriver = new EdgeDriver();
                break;
            default:
                returnDriver = null;
        }
        return returnDriver;
    }

    public void setTimeout(final int seconds) {
        driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
    }

    public WebElement getElement(final String strBy, final String value) {
        System.out.println("Finding element by " + strBy + " with value " + value);
        pageWait();
        final WebElement element = null;
        switch (strBy.toLowerCase()) {
            case "id":
                return driver.findElement(By.id(value));
            case "name":
                return driver.findElement(By.name(value));
            case "xpath":
                return driver.findElement(By.xpath(value));
            case "class":
                return driver.findElement(By.className(value));
            case "linktext":
                return driver.findElement(By.linkText(value));
            case "partiallinktext":
                return driver.findElement(By.partialLinkText(value));
            case "tagname":
                return driver.findElement(By.tagName(value));
            case "css":
                return driver.findElement(By.cssSelector(value));
            // advanced created xpath here already, new can also be added
            case "idcontains":
                return driver.findElement(By.xpath("//*[contains(@id,'" + value + "')]"));
            case "textcontains":
                return driver.findElement(By.xpath("//*[contains(text(),'" + value + "')]"));
            case "text":
                return driver.findElement(By.xpath("//*[@text='" + value + "']"));
            case "classcontains":
                return driver.findElement(By.xpath("//*[contains(@class,'" + value + "')]"));

            default:
                System.out.println("No valid identifier or by is found By value is " + strBy);
                return null;
        }

    }

    public WebElement getElement(final String[] locator) {
        return getElement(locator[0], locator[1]);
    }

    public WebElement getElement(final WebElement element, final String strBy, final String value) {
        System.out.println("Finding element by " + strBy + " with value " + value);
        switch (strBy.toLowerCase()) {
            case "id":
                return element.findElement(By.id(value));
            case "name":
                return element.findElement(By.name(value));
            case "xpath":
                return element.findElement(By.xpath(value));
            case "class":
                return element.findElement(By.className(value));
            case "linktext":
                return element.findElement(By.linkText(value));
            case "partiallinktext":
                return element.findElement(By.partialLinkText(value));
            case "tagname":
                return element.findElement(By.tagName(value));
            case "css":
                return element.findElement(By.cssSelector(value));
            // advanced created xpath here already, new can also be added
            case "idcontains":
                return element.findElement(By.xpath("//*[contains(@id,'" + value + "')]"));
            case "textcontains":
                return element.findElement(By.xpath("//*[contains(text(),'" + value + "')]"));
            case "text":
                return element.findElement(By.xpath("//*[@text='" + value + "']"));
            case "classcontains":
                return element.findElement(By.xpath("//*[contains(@class,'" + value + "')]"));

            default:
                System.out.println("No valid identifier or by is found By value is " + strBy);
                return null;
        }

    }

    public List<WebElement> getElements(final String[] locator) {
        return getElements(locator[0], locator[1]);
    }

    public List<WebElement> getElements(final String strBy, final String value) {
        System.out.println("Finding element by " + strBy + " with value " + value);
        switch (strBy.toLowerCase()) {
            case "id":
                return driver.findElements(By.id(value));
            case "name":
                return driver.findElements(By.name(value));
            case "xpath":
                return driver.findElements(By.xpath(value));
            case "class":
                return driver.findElements(By.className(value));
            case "linktext":
                return driver.findElements(By.linkText(value));
            case "partiallinktext":
                return driver.findElements(By.partialLinkText(value));
            case "tagname":
                return driver.findElements(By.tagName(value));
            case "css":
                return driver.findElements(By.cssSelector(value));
            // advanced created xpath here already, new can also be added
            case "idcontains":
                return driver.findElements(By.xpath("//*[contains(@id,'" + value + "')]"));
            case "textcontains":
                return driver.findElements(By.xpath("//*[contains(text(),'" + value + "')]"));
            case "text":
                return driver.findElements(By.xpath("//*[@text='" + value + "']"));

            default:
                System.out.println("No valid identifier or by is found By value is " + strBy);
                return null;
        }

    }

    public List<WebElement> getElements(final WebElement element, final String strBy, final String value) {
        System.out.println("Finding element by " + strBy + " with value " + value);
        switch (strBy.toLowerCase()) {
            case "id":
                return element.findElements(By.id(value));
            case "name":
                return element.findElements(By.name(value));
            case "xpath":
                return element.findElements(By.xpath(value));
            case "class":
                return element.findElements(By.className(value));
            case "linktext":
                return element.findElements(By.linkText(value));
            case "partiallinktext":
                return element.findElements(By.partialLinkText(value));
            case "tagname":
                return element.findElements(By.tagName(value));
            case "css":
                return element.findElements(By.cssSelector(value));
            // advanced created xpath here already, new can also be added
            case "idcontains":
                return element.findElements(By.xpath("//*[contains(@id,'" + value + "')]"));
            case "textcontains":
                return element.findElements(By.xpath("//*[contains(text(),'" + value + "')]"));
            case "text":
                return element.findElements(By.xpath("//*[@text='" + value + "']"));

            default:
                System.out.println("No valid identifier or by is found By value is " + strBy);
                return null;
        }

    }

    public void setText(final WebElement element, final String data) {
        element.clear();
        element.sendKeys(data);
    }

    public void setText(final String strBy, final String value, final String data) {
        setText(getElement(strBy, value), data);
    }

    public void setText(final String[] locator, final String data) {
        setText(locator[0], locator[1], data);
    }

    public void click(final WebElement element) {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Configs.explicitTimeout));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    	element.click();
    }

    public void click(final String strBy, final String value) {
        click(getElement(strBy, value));
    }

    public void click(final String[] locator) {
        click(locator[0], locator[1]);
    }

    public String getText(final WebElement element) {
        return element.getText();
    }

    public String getText(final String strBy, final String value) {
        return getText(getElement(strBy, value));
    }

    public String getText(final String[] locator) {
        return getText(getElement(locator[0], locator[1]));
    }

    public void hoverOverElement(final String strBy, final String value) {
        final WebElement element = getElement(strBy, value);
        final Actions action = new Actions(driver);
        action.moveToElement(element).perform();
        // OR
        // ((JavascriptExecutor)driver).executeScript("var mouseEvent = document.createEvent('MouseEvents');mouseEvent.initEvent('mouseover', true, true);
        // arguments[0].dispatchEvent(mouseEvent);", element);
    }

    public void hoverOverElement(final String[] locator) {
        hoverOverElement(locator[0], locator[1]);
    }

    public void getParent() {

    }

    public void selectItemDropDown(final String by, final String valueSelector, final String selectData) {
        final Select drpCountry = new Select(getElement(by, valueSelector));
        drpCountry.selectByVisibleText(selectData);
    }

    public void selectItemDropDown(final String[] locator, final String selectData) {
        selectItemDropDown(locator[0], locator[1], selectData);
    }

    public boolean checkElementPresent(final String[] locator) {
        return checkElementPresent(locator[0], locator[1]);
    }

    public boolean checkElementPresent(final String by, final String value) {
        try {
            getElement(by, value);
            return true;
        } catch (final Exception e) {
            return false;
        }
    }

    // hardcoded for now
    public void scrollPage() {
        final JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,600)");
    }
    
    public void pageWait() {
    	try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    

}

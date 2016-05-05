import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LetterChrome {
	
	protected WebDriver webDriver;

	@BeforeMethod
	public void setup() {
		setChromeDriver();
		webDriver = new ChromeDriver();
	}
	
	@AfterMethod
	public void closeBrowser() {
		webDriver.quit();
    }
	
	@Test
	public void letterExistTestChrome() throws InterruptedException {
		webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		webDriver.get("https://www.google.com.ua");
		WebElement googleSearchTextBox = webDriver.findElement(By.xpath("//input[@id='lst-ib']"));
		Wait<WebDriver> wait = new FluentWait<WebDriver>(webDriver).withTimeout(
				60, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
		wait.until(ExpectedConditions.visibilityOf(googleSearchTextBox));
		WebDriverWait wbWait = new WebDriverWait(webDriver, 60);
		wbWait.until(ExpectedConditions.visibilityOf(googleSearchTextBox));
		
		System.out.println("Google searched page is opening...");
		webDriver.findElement(By.xpath("//*[@id='gbw']/div/div/div[1]/div[1]/a")).click();
//		webDriver.findElement(By.xpath("//a[contains(@href,'https://mail.google.com/mail/?tab=wm')]")).click();
		WebElement mailTextBox = webDriver.findElement(By.xpath("//input[@id='Email']"));
		Wait<WebDriver> mailFieldWait = new FluentWait<WebDriver>(webDriver).withTimeout(
				60, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
		mailFieldWait.until(ExpectedConditions.visibilityOf(mailTextBox));
		WebDriverWait wbWait1 = new WebDriverWait(webDriver, 60);
		wbWait1.until(ExpectedConditions.visibilityOf(mailTextBox));
		
		System.out.println("Gmail login page is opening...");
		
		mailTextBox.sendKeys("ivanna.lits");
		mailTextBox.sendKeys(Keys.ENTER);
		WebElement passwordTextBox = webDriver.findElement(By.xpath("//input[@id='Passwd']"));

		System.out.println("Login is correct. Password login page is opening...");
		
		passwordTextBox.sendKeys("ivannalits");
		passwordTextBox.sendKeys(Keys.ENTER);
		WebElement mailPage = webDriver.findElement(By.xpath("//div[@id='gs_lc50']/input"));
		System.out.println("Password is correct. Gmail page is opening...");

		mailPage.sendKeys("Hello User");
		mailPage.sendKeys(Keys.ENTER);

        System.out.println("Theme 'Hello User' is typed in the searched field. Letter with 'Hello User' theme is searching...");

        List<WebElement> searchResult = webDriver.findElements(By.xpath("//div[@id=':4x']/div"));     
        if (searchResult.isEmpty()) {
        	Assert.assertTrue(searchResult.isEmpty(), "Letter with 'Hello User' theme is found");
        	System.out.println("Letter with 'Hello User' theme is found :)");
		} else {
			System.out.println("Letter with 'Hello User' theme is not found :(");
			
		}
        
	}

	private void setChromeDriver() {
		String chromeBinary = "src/drivers/chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", chromeBinary);
	}
	
	
	
}

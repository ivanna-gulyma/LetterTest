import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LetterFF {

	@Test
	public void letterExistTestFF() throws InterruptedException {
		WebDriver driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://www.google.com.ua");
		WebElement googleSearchTextBox = driver.findElement(By.xpath("//input[@id='lst-ib']"));
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(
				60, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
		wait.until(ExpectedConditions.visibilityOf(googleSearchTextBox));
		WebDriverWait wbWait = new WebDriverWait(driver, 60);
		wbWait.until(ExpectedConditions.visibilityOf(googleSearchTextBox));

		System.out.println("Google searched page is opened...");
		
		driver.findElement(By.xpath("//a[contains(@href,'https://mail.google.com/mail/?tab=wm')]")).click();
		WebElement mailTextBox = driver.findElement(By.xpath("//input[@id='Email']"));
		Wait<WebDriver> mailFieldWait = new FluentWait<WebDriver>(driver).withTimeout(
				60, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
		mailFieldWait.until(ExpectedConditions.visibilityOf(mailTextBox));
		WebDriverWait wbWait1 = new WebDriverWait(driver, 60);
		wbWait1.until(ExpectedConditions.visibilityOf(mailTextBox));
		
		System.out.println("Gmail login page is opened...");
		
		mailTextBox.sendKeys("ivanna.lits");
		mailTextBox.sendKeys(Keys.ENTER);
		WebElement passwordTextBox = driver.findElement(By.xpath("//input[@id='Passwd']"));

		System.out.println("Login is correct. Password login page is opened...");
		
		passwordTextBox.sendKeys("ivannalits");
		passwordTextBox.sendKeys(Keys.ENTER);
		WebElement mailPage = driver.findElement(By.cssSelector("//div[@id='gs_lc50']/input"));
		
		System.out.println("Password is correct. Gmail page is opened...");

		mailPage.sendKeys("Hello User");
		mailPage.sendKeys(Keys.ENTER);

        System.out.println("Theme 'Hello User' is typed in the searched field. Letter with 'Hello User' theme is searching...");

        List<WebElement> searchResult = driver.findElements(By.xpath("//div[@id=':4x']/div")); 
        	 if (searchResult.isEmpty()) {
            	Assert.assertTrue(searchResult.isEmpty(), "Letter with 'Hello User' theme is found :)");
            	System.out.println("Letter with 'Hello User' theme is found");
    		} else {
    			Assert.assertFalse(searchResult.isEmpty(), "Letter with 'Hello User' theme is not found :(");
    			System.out.println("Letter with 'Hello User' theme is not found");
    			
    	}
		driver.quit();
	}

}

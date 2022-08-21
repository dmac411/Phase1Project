package test;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AmazonIndia {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

		WebDriver driver = new ChromeDriver();

		driver.get("https://amazon.in");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);

		
		//enter search word and press enter
		WebElement SearchWord = driver.findElement(By.xpath("//input[@id = 'twotabsearchtextbox']"));

		SearchWord.sendKeys("Samsung");
		driver.findElement(By.xpath("//input[@id = 'nav-search-submit-button']")).sendKeys(Keys.ENTER);
		driver.findElement(By.xpath("//div[@data-component-type='s-search-result']//h2"));
		driver.findElement(By.xpath("//div[@data-component-type='s-search-result']//span[@class='a-price']"));

		List<WebElement> AllProducts = driver
				.findElements(By.xpath("//div[@data-component-type='s-search-result']//h2/a"));

		List<WebElement> AllPrice = driver
				.findElements(By.xpath("//div[@data-component-type='s-search-result']//span[@class='a-price']"));

		System.out.println("product count " + AllProducts.size());

		// list products and prices using for loop
		for (int i=0;i<AllProducts.size(); i++) {
			System.out.println(AllProducts.get(i).getText() + " " + AllPrice.get(i).getText());
		}
			// Get window handler

			String ParentWH = driver.getWindowHandle();
			String ExpectedValue = AllProducts.get(0).getText();

			// click on first item

			AllProducts.get(0).click();

			Set<String> AllWindowHandler = driver.getWindowHandles();
			for (String win : AllWindowHandler) {
				System.out.println(win);

				if (!win.equals(ParentWH)) {
					driver.switchTo().window(win);
				}
				;
			}

			// compare title
			WebElement title = driver.findElement(By.id("productTitle"));

			String str = title.getText();

			if (str.equals(ExpectedValue)) {

				System.out.println("Title matches");
			} else {
				System.out.println("Title does not match");
			}

			driver.quit();

		}

	}


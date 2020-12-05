package test;

import org.testng.annotations.Test;
import helper.GlobalVariables;
import pageobjects.MainPage;
import pageobjects.ProductDetailsPage;
import org.testng.annotations.BeforeTest;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class ShoppingTest {

	private static WebDriver driver;

	@BeforeTest
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver", "lib/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test(priority = 1)
	public void testShopping() {
		driver.get(GlobalVariables.applicationURL);
		MainPage mainPage = new MainPage(driver);
		mainPage.waitFor(mainPage.getMainPageMenu("HOME").waitUntilVisible());
		mainPage.waitFor(mainPage.getMainPageMenu("MEN").waitUntilVisible());
		mainPage.waitFor(mainPage.getMainPageMenu("WOMEN").waitUntilVisible());
		mainPage.waitFor(mainPage.getMainPageMenu("RUNNING").waitUntilVisible());
		Assert.assertTrue(mainPage.getLogo().isDisplayed(), "Logo is not displayed");
	}

	@Test(priority = 2)
	public void testFilteredProductGrid() throws InterruptedException {
		driver.get(GlobalVariables.applicationURL);
		MainPage mainPage = new MainPage(driver);
		mainPage.waitFor(mainPage.getMainPageMenu("HOME").waitUntilVisible());
		mainPage.waitFor(mainPage.getMainPageMenu("MEN").waitUntilVisible());
		mainPage.waitFor(mainPage.getMainPageMenu("WOMEN").waitUntilVisible());
		mainPage.waitFor(mainPage.getMainPageMenu("RUNNING").waitUntilVisible());
		mainPage.waitFor(mainPage.getBackColorFilter());
		mainPage.getBackColorFilter().click();
		mainPage.waitFor(mainPage.getMainPageButtons("Filter"));
		mainPage.getMainPageButtons("Filter").click();
		mainPage.waitFor(mainPage.getProductGrid());
	}

	@Test(priority = 3)
	public void testProductDetails() throws InterruptedException {
		driver.get(GlobalVariables.applicationURL);
		MainPage mainPage = new MainPage(driver);
		mainPage.waitFor(mainPage.getMainPageMenu("HOME").waitUntilVisible());
		mainPage.waitFor(mainPage.getMainPageMenu("MEN").waitUntilVisible());
		mainPage.waitFor(mainPage.getMainPageMenu("WOMEN").waitUntilVisible());
		mainPage.waitFor(mainPage.getMainPageMenu("RUNNING").waitUntilVisible());
		mainPage.waitFor(mainPage.getMainPageProduct("Appli Air x Night"));
		mainPage.getMainPageProduct("Appli Air x Night").click();
		ProductDetailsPage ProductDetailsPage = new ProductDetailsPage(driver);
		ProductDetailsPage.waitFor(ProductDetailsPage.getProductName("Appli Air x Night"));
		Assert.assertTrue(ProductDetailsPage.getProductName("Appli Air x Night").isDisplayed(),
				"Product Details is not displayed");
	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}
}
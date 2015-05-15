package com.casofuncional.busquedaengoogle3;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AppTest {

	private FirefoxDriver elDriver;

	@Before
	public void before() {
		elDriver = new FirefoxDriver();
	}

	@Test
	public void test() {
		elDriver.get("https://www.google.com.ar/");

		elDriver.findElement(By.id("lst-ib")).sendKeys("Nicolas Mori");
		elDriver.findElement(By.name("btnG")).click();
		
		WebElement myDynamicElement = (new WebDriverWait(elDriver, 10))
				  .until(ExpectedConditions.presenceOfElementLocated(By.className("med")));
	
		Assert.assertTrue(elDriver.findElement(By.className("med")).isEnabled());
		
		
	}
	
	@After
	public void afterClass() {
		elDriver.quit();
	}
	
}

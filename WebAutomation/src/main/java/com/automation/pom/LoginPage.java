package com.automation.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class LoginPage {
WebDriver driver;
	
	@FindBy(xpath = "//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a")
	private WebElement linkSignIn;
	
	@FindBy(xpath = "//*[@id=\"email\"]")
	private WebElement inputEmail;
	
	@FindBy(xpath = "//*[@id=\"passwd\"]")
	private WebElement inputPassword;
	
	@FindBy(xpath = "//*[@id=\"SubmitLogin\"]/span")
	private WebElement btnSignIn;
	
	@FindBy(xpath = "//*[@id=\"header\"]/div[2]/div/div/nav/div[2]/a")
	private WebElement btnSignOut;
	
	@FindBy(xpath = "//*[@id=\"columns\"]/div[1]/span[2]")
	private WebElement controller;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void Login(String email, String password) throws InterruptedException{
		linkSignIn.click();
		Thread.sleep(2000);
		inputEmail.sendKeys(email);
		inputPassword.sendKeys(password);
		btnSignIn.click();
		Thread.sleep(1000);
		Assert.assertEquals(controller.getText(), "My account");
		btnSignOut.click();
		Thread.sleep(2000);
	}
}

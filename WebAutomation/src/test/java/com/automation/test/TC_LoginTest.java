package com.automation.test;

import java.io.FileInputStream;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.automation.base.DriverInstance;
import com.automation.pom.LoginPage;
import com.automation.utils.CaptureScreenshots;

public class TC_LoginTest extends DriverInstance {
	
	@Test(dataProvider = "Excel")
	public void Test(String email, String password) throws InterruptedException {
		LoginPage login = new LoginPage(driver);
		login.Login(email, password);
	}
	
	@DataProvider(name = "Excel")
	public Object [][] testDataGenerator() throws Exception{
		FileInputStream fis = new FileInputStream("./data/data_test.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet loginSheet = wb.getSheet("Login");
		int numberOfData = loginSheet.getPhysicalNumberOfRows();
		Object [][] testData = new Object [numberOfData][2];
		for( int i = 0; i < numberOfData; i++) {
			XSSFRow rw = loginSheet.getRow(i);
			XSSFCell email = rw.getCell(0);
			XSSFCell password = rw.getCell(1);
			testData [i][0] = email.getStringCellValue();
			testData [i][1] = password.getStringCellValue();
		}
		return testData;
	}
	@AfterMethod
	public void takeScreenshot(ITestResult result) throws InterruptedException {
		Thread.sleep(2000);
		if(ITestResult.FAILURE == result.getStatus()) {
			String email = (String)result.getParameters()[0];
			int index = email.indexOf("@");
			String accountName = email.substring(0, index);
			CaptureScreenshots.captureScreenshot(driver, accountName);
		}
	}
}

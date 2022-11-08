package com.automation.utils;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

public class CaptureScreenshots {
	static String filePath = null;
	private static String pjPath = System.getProperty("user.dir")+"/";
	
	public static void captureScreenshot(WebDriver driver, String name) {
		try {
			System.setProperty("org.uncommons.reportng.escape-output", "false");
			TakesScreenshot ts = (TakesScreenshot) driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			File theDir = new File(pjPath + "Screenshots");
			if(!theDir.exists()) {
				theDir.mkdir();
			}
			filePath = theDir + "/" + name + ".png";
			File image = new File(filePath);
			FileUtils.copyFile(source, image);
			
			Reporter.log("<br>Screenshot taken current URL: "); 
            Reporter.log("<a href=\""+ image.getAbsolutePath()+"\">"
            		+ "<img src='"+ image.getAbsolutePath() +"'height='240' width='418'/></a><br>");
            System.out.println("Added Image" + "\n" + image.getAbsolutePath());
		}catch(Exception ex) {
			System.out.println("Fail to take screenshot" + ex.getMessage());
		}
	}
}

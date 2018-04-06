package com.pom.testcases;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.pom.pages.LoginPage;
import com.pom.wrappers.ProjectWrappers;

import atu.testng.reports.ATUReports;
import atu.testng.reports.listeners.ATUReportsListener;
import atu.testng.reports.listeners.ConfigurationListener;
import atu.testng.reports.listeners.MethodListener;
import atu.testng.reports.utils.Utils;

@Listeners({ ATUReportsListener.class, ConfigurationListener.class, MethodListener.class , com.pom.utils.TestResultCount.class })

public class LoginBCT extends ProjectWrappers {
	{
		System.setProperty("atu.reporter.config", "./resources/atu.properties");
		/*System.setProperty("mail.smtp.host", "10.101.3.229");
		System.setProperty("mail.smtp.port", "8080");*/
		ATUReports.currentRunDescription = "Test cases for BCT login Pass, Fail and Skip";
	}
	
	

	@BeforeSuite
	public void setSheetName() {
		dataSheetName = "BCT2";
		
	}
      
	@Test(dataProvider = "fetchData")

	public void loginPass(String userId, String password) throws InterruptedException {

		ATUReports.setAuthorInfo(prop.getProperty("Authors"), Utils.getCurrentTime(), "1.0");
		ATUReports.setWebDriver(driver);
		ATUReports.setTestCaseReqCoverage("Test case for BCT Login pass");
		
		
		new LoginPage(driver, test).enterUserId(userId).enterPassword(password).clickSubmit().getUserName();
		
		//Thread.sleep(30000);
		

	}   
	
	//some change

	@Test(dataProvider = "fetchData")
	
	public void loginFail(String userId, String password) throws InterruptedException {

		ATUReports.setAuthorInfo(prop.getProperty("Authors"), Utils.getCurrentTime(), "1.0");
		ATUReports.setWebDriver(driver);
		ATUReports.setTestCaseReqCoverage("Test case for BCT Login fail");

		new LoginPage(driver, test).enterUserId(userId).enterPasswordWrong(password).clickSubmit();

	}

	@Test(dataProvider = "fetchData", dependsOnMethods = "loginFail")
	
	public void loginSkip(String userId, String password) throws InterruptedException {

		ATUReports.setAuthorInfo(prop.getProperty("Authors"), Utils.getCurrentTime(), "1.0");
		ATUReports.setWebDriver(driver);
		ATUReports.setTestCaseReqCoverage("Test case for BCT Login fail");

		new LoginPage(driver, test).enterUserId(userId).enterPasswordWrong(password).clickSubmit();

	}


	@Test(dataProvider = "fetchData", dependsOnMethods = "loginSkip")
	
	public void loginSkip2(String userId, String password) throws InterruptedException {

		ATUReports.setAuthorInfo(prop.getProperty("Authors"), Utils.getCurrentTime(), "1.0");
		ATUReports.setWebDriver(driver);
		ATUReports.setTestCaseReqCoverage("Test case for BCT Login fail");

		new LoginPage(driver, test).enterUserId(userId).enterPasswordWrong(password).clickSubmit();

	}
}

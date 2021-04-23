package tests;

import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;		

import pages.GooglePage;
import pages.RouteOnePage;

public class GoogleTest {		
	    WebDriver driver;
	    GooglePage objGoogleSearch;
	    RouteOnePage objRouteOne;

		@BeforeMethod
		public void beforeTest() 
		{	
	        driver = new ChromeDriver();
		}		
		
		/**
	     * Navigate to https://www.google.com
	     * Verify Google Home Page & Logo
	     * Search for 'RouteOne'
	     * Loop through 1st page of results to find https://www.routeone.com/
	     * Click on https://www.routeone.com/
	     * Verify RouteOne Home Page & Logo
	     */
		@Test				
		public void googleTest()
		{	
			//Create Google Page object
			objGoogleSearch = new GooglePage(driver);
	
			//Navigate to Google
	        driver.get("https://www.google.com");

	        //Wait for Google Page to Load
	        objGoogleSearch.waitForPageToLoadByXpath("//form[@action='/search']");
	        
	        //Verify Google Home Page
	        objGoogleSearch.verifyGoogleHomePage();
	        
		    //Set input value for Search & Enter
		    objGoogleSearch.setInputValue("RouteOne");

		    //Wait for results to load
	        objGoogleSearch.waitForPageToLoadById("rcnt");
	        
	        //loop through results on first page for www.routeone.com and click it
	        objGoogleSearch.selectGoogleResult("https://www.routeone.com"); 
	        
	        //Create RouteOne Page object
	        objRouteOne = new RouteOnePage(driver);
			
	        //Wait for RouteOne Page to Load
	        objRouteOne.waitForPageToLoad("page");
	        
	        //Verify RouteOne Logo
	        objRouteOne.verifyRouteOneHomePageLogo();
		}	
		
		@AfterMethod
		public void afterTest() 
		{
			//driver.quit();			
		}		
}
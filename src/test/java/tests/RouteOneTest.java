package tests;

import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;		

import pages.RouteOnePage;

public class RouteOneTest {		
	    WebDriver driver;
	    RouteOnePage objRouteOne;
	    
		@BeforeMethod
		public void beforeTest() 
		{	
	        driver = new ChromeDriver();
		}		
		
		/**
	     * Navigate to https://www.routeone.com/
	     * Verify RouteOne Home Page & Logo
	     * Loop through nav bar to select ABOUT US
	     * Look through sub nav bar to select CAREERS
	     * Go to Career Center
	     * Search for Jobs
	     * Verify Job Opening Results
	     */
		@Test				
		public void routeOneTest()
		{	
			//Create RouteOne Page object
			objRouteOne = new RouteOnePage(driver);
	
			//Navigate to RouteOne
	        driver.get("https://www.routeone.com/");

	        //Wait for RouteOne Page to Load
	        objRouteOne.waitForPageToLoad("page");
	        
	        //Verify RouteOne Logo
	        objRouteOne.verifyRouteOneHomePageLogo();

	        //Select About Us on Navbar
			objRouteOne.hoverOverTabOnNavBar("about-us");
			
			//Select Careers on Sub navbar
			objRouteOne.selectLinkOnSubNav("careers");

	        //Wait for RouteOne Page to Load
	        objRouteOne.waitForPageToLoad("page");
			
	        //Verify Career Page
	        objRouteOne.verifyRouteOneCareerPage();
	     
	        //Navigate to Career Center
	        objRouteOne.navigateToCareerCenter();

		    //Wait for Career Center to load
	        objRouteOne.waitForPageToLoad("careercenter-main-container");	        

	        //Scroll into view of job opening search bar
	        objRouteOne.scrollToElement(".mdf-searchbox-container > .mdf-searchbox-input");

	        //Set input value in search
			objRouteOne.setInputValue("test");

		    //Wait for Filtered Job Opening Search Results
	        objRouteOne.waitForPageToLoad("current-openings-content");	        

	        //Verify number of job openings, position, country
	        objRouteOne.verifyJobOpenings("Software Test Engineer", "US", 5);
		}	
		
		@AfterMethod
		public void afterTest() 
		{
			driver.quit();			
		}		
}
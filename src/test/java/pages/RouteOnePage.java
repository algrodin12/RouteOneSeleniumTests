package pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class RouteOnePage {

    WebDriver driver;

    public RouteOnePage(WebDriver driver)
    {
        this.driver = driver;
    }
    
    //Wait for RouteOne page to load
    public void waitForPageToLoad(String pageClassName)
    {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(pageClassName)));
    }
    
    //Verify RouteOne home page logo
    public void verifyRouteOneHomePageLogo()
    {
    	//Verify RouteOne Logo is displayed
        Assert.assertTrue(driver.findElement(By.id("logo")).isDisplayed());
        
        //Verify RouteOne logo
	    Assert.assertEquals(driver.findElement(By.tagName("img")).getAttribute("alt"), "RouteOne");
    }
    
    //Verify Career Page
    public void verifyRouteOneCareerPage()
    {
    	//Verify Page Title is displayed
		Assert.assertTrue(driver.findElement(By.cssSelector("h1#page-title")).isDisplayed());
		
		//Verify Page Title is Careers
		Assert.assertTrue(driver.findElement(By.cssSelector("h1#page-title")).getText().equals("Careers"));
    }
    
    //Scroll to element on page
    public void scrollToElement(String elementCss)
    {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.cssSelector(elementCss)));
    }

    //Navigate to Career Center in new window
    public void navigateToCareerCenter()
    {
	    //Scroll into view of VIEW ALL CURRENT OPENINGS button
	    scrollToElement(".button.cta");
	 
	    //Verify button says VIEW ALL CURRENT OPENINGS
	    Assert.assertTrue(driver.findElement(By.cssSelector(".button.cta")).getText().equals("VIEW ALL CURRENT OPENINGS"));
		
	    //Click on VIEW ALL CURRENT OPENINGS button
	    driver.findElement(By.cssSelector(".button.cta")).click();
	    
	    // Switch to new window opened
	    for(String winHandle : driver.getWindowHandles()){
	        driver.switchTo().window(winHandle);
	    } 
    }

    //Select tab on nav bar
    public void hoverOverTabOnNavBar(String tabName)
    {
    	WebElement navBar = driver.findElement(By.cssSelector(".tb-megamenu-nav.nav.level-0"));
    	List<WebElement> tabs = navBar.findElements(By.cssSelector(".tb-megamenu-item.level-1"));
    	for (WebElement tab : tabs) 
    	{
    		if (tab.findElement(By.tagName("a")).getAttribute("href").contains(tabName))
			{
    			Actions hover = new Actions(driver);
    			hover.moveToElement(tab).build().perform();
			}
    	}
    }

	//Select link on sub nav
    public void selectLinkOnSubNav(String linkName)
    {
    	WebElement subNav = driver.findElement(By.cssSelector(".tb-megamenu-subnav.mega-nav.level-1"));
    	List<WebElement> links = subNav.findElements(By.cssSelector(".tb-megamenu-item.level-2"));
    	for (WebElement link : links) 
    	{
    		if (link.findElement(By.tagName("a")).getAttribute("href").contains(linkName))
			{
				link.click();
				break;
			}
    	}
    }
    
    //Verify Job Opening Results
    public void verifyJobOpenings(String jobName, String jobCountry, Integer jobSize)
    {
    	WebElement jobOpenings = driver.findElement(By.className("current-openings-list"));
    	List<WebElement> jobs = jobOpenings.findElements(By.className("current-openings-item"));
    	
    	//Verify the number of job opening results
    	Assert.assertTrue(Integer.valueOf(jobs.size()).equals(jobSize));
    	
    	for (WebElement job : jobs) 
    	{
    		//Verify Job Name
    		if (job.findElement(By.cssSelector(".current-openings-details > span")).getText().contentEquals(jobName)) {
	    		Assert.assertTrue(job.findElement(By.cssSelector(".current-openings-details > span")).getText().contains(jobName));
	    		//Verify Job Country
	    		Assert.assertTrue(job.findElement(By.cssSelector(".current-opening-locations > .location-row > .current-opening-location-item > span")).getText().contains(jobCountry));
    		}
    	}
    }
    
    
    //Set input value of RouteOne job search
    public void setInputValue(String input)
    {
        driver.findElement(By.className("mdf-searchbox-input")).sendKeys(input);
        driver.findElement(By.className("mdf-searchbox-input")).sendKeys(Keys.ENTER);
    }

}
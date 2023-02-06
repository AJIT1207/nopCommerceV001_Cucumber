package stepDefinitions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import io.cucumber.java.Before;
import io.cucumber.java.en.*;
//import junit.framework.Assert;
import pageObjects.AddcustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;

public class Steps extends baseClass{

	@Before
	public void setup() throws IOException
	{
		
		logger = Logger.getLogger("nopCommerce"); //Added logger
		PropertyConfigurator.configure("Log4j.properties"); //Added Logger
		
		//Reading properties file
		configProp=new Properties();
		FileInputStream configPropfile = new FileInputStream("config.properties");
		configProp.load(configPropfile);
		
		
		String br = configProp.getProperty("browser");
		
		if(br.equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", configProp.getProperty("chromepath"));
	        driver = new ChromeDriver();
	        logger.info("*******Launching browser********");
	        driver.manage().window().maximize(); // maximize window
	        driver.manage().deleteAllCookies(); // delete all the cookies
		}
		
		else if(br.equalsIgnoreCase("firefox"))
		{
		System.setProperty("webdriver.gecko.driver", configProp.getProperty("firefoxpath"));
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		}
		
		else if(br.equalsIgnoreCase("edge"))
		{
		System.setProperty("webdriver.edge.driver", configProp.getProperty("msedgepath"));
		driver = new EdgeDriver();
		driver.manage().window().maximize();
		}
		
	
	}
	
	@Given("User Launch Chrome browser")
	public void user_launch_chrome_browser() {
		
		lp=new LoginPage(driver);
	}

	@When("User opens URL {string}")
	public void user_opens_url(String url) {
		  logger.info("*******opening URLr********");
		driver.get(url);
	}

	@When("User enters Email as {string} and Password as {string}")
	public void user_enters_email_as_and_password_as(String email, String password) {
		  logger.info("*******Providing crdentials********");
	   lp.setUserName(email);
	   lp.setPassword(password);
	}

	@When("Click on Login")
	public void click_on_login() {
	  lp.clickLogin();
	}

	@SuppressWarnings("deprecation")
	@Then("Page Title should be {string}")
	public void page_title_should_be(String title) {
	    if(driver.getPageSource().contains("Login was unsuccessfull."))
	    		{
	    			driver.close();	
	    			Assert.assertTrue(false);
	    		}
	    else
	    {
	    	Assert.assertEquals(title, driver.getTitle());
	    }
	}

	@When("User click on Log out link")
	public void user_click_on_log_out_link() {
	   lp.clickLogout();
	   
	}

	@Then("close browser")
	public void close_browser() {
	    driver.quit();
	}
	
	//Add Customers features
	@Then("User can view Dashboad")
	public void user_can_view_dashboad() {
		
		addCust = new AddcustomerPage(driver);
		Assert.assertEquals("Dashboard / nopCommerce administration", addCust.getPageTitle());
	  
	}
	@When("User click on customers Menu")
	public void user_click_on_customers_menu() throws InterruptedException {
		Thread.sleep(3000);
		addCust.clickOnCustomersMenu();
	}
	
	@When("click on customers Menu Item")
	public void click_on_customers_menu_item() throws InterruptedException {	
		Thread.sleep(3000);
		addCust.clickOnCustomersMenuItem(); 
		Thread.sleep(3000);
	}
	

	 @When("click on Add new button")
	public void click_on_add_new_button() throws InterruptedException {
		Thread.sleep(3000);
		addCust.clickOnAddnew();
	    
	}
	@Then("User can view Add new customer page")
	public void user_can_view_add_new_customer_page() {
		Assert.assertEquals("Add a new customer / nopCommerce administration", addCust.getPageTitle());
	   
	}
	@When("User enter customer info")
	public void user_enter_customer_info() throws InterruptedException {
	    String email = randomestring()+"@yahoo.com";
	    addCust.setEmail(email);
	    addCust.setPassword("test123");
		// Registered - default
		// The customer cannot be in both 'Guests' and 'Registered' customer roles
		// Add the customer to 'Guests' or 'Registered' customer role
		addCust.setCustomerRoles("Guest");
		Thread.sleep(3000);

		addCust.setManagerOfVendor("Vendor 2");
		addCust.setGender("Male");
		addCust.setFirstName("Ajit");
		addCust.setLastName("Kumar");
		addCust.setDob("7/05/1985"); // Format: D/MM/YYY
		addCust.setCompanyName("busyQA");
		addCust.setAdminContent("This is for testing.........");
	}
	@When("click on Save button")
	public void click_on_save_button() throws InterruptedException {
	  addCust.clickOnSave();
	  driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	  Thread.sleep(3000);
	  
	}
	//@SuppressWarnings("deprecation")
	@Then("User can view confirmation message {string}")
	public void user_can_view_confirmation_message(String msg) {
		Assert.assertTrue(driver.findElement(By.tagName("body")).getText()
				.contains("The new customer has been added successfully"));
	}

	
	//Steps for searching a customer using ID
	@When("Enter customer EMail")
	public void enter_customer_e_mail() {
	   searchCust = new SearchCustomerPage(driver);
	   searchCust.setEmail("victoria_victoria@nopCommerce.com");
	}
	
	
	@When("Click on search button")
	public void click_on_search_button() throws InterruptedException {
	   searchCust.clickSearch();
	   Thread.sleep(3000);
	}
	@Then("User should found Email in the Search table")
	public void user_should_found_email_in_the_search_table() {
	   boolean status = searchCust.searchCustomerByEmail("victoria_victoria@nopCommerce.com");
	   
	   Assert.assertEquals(true, status);
	}

	//Steps for Searching a customer with first and last name
	@When("Enter customer FirstName")
	public void enter_customer_first_name() {
		searchCust = new SearchCustomerPage(driver);
		searchCust.setFirstName("Victoria");
	}
	@When("Enter customer LastName")
	public void enter_customer_last_name() {
		searchCust = new SearchCustomerPage(driver);
		searchCust.setLastName("Terces");
	}
	@Then("User should found Name in the Search table")
	public void user_should_found_name_in_the_search_table() {
		boolean status = searchCust.searchCustomerByName("Victoria Terces");
		Assert.assertEquals(true, status);
	}

	
}

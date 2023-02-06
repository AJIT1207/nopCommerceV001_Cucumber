package stepDefinitions;

import VowelCount.VowelCount;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.messages.types.Product;

public class Searchsteps {
	
	VowelCount count;
	
	
	@Given("I have a search field on Amazon page")
	public void i_have_a_search_field_on_amazon_page() {
	    // Write code here that turns the phrase above into concrete actions
		System.out.println("Step1: I am on search page");
	   
	}

	@When("I search for a product with name {string} and price {int}")
	public void i_search_for_a_product_with_name_and_price(String productName, Integer price) {
		
		System.out.println("Step2: Productname  " + productName + "  and the price " +price+ "");
		
		count = new VowelCount(productName, price); 
	  
	}

	@Then("Product with name {string} should be displayed")
	public void product_with_name_should_be_displayed(String productName) {
	    // Write code here that turns the phrase above into concrete actions
		System.out.println("Step3: Productname  " + productName + " is displayed ");
	
	}


}

package es.uniovi.asw.steps;

import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import es.uniovi.asw.Factory;
import es.uniovi.asw.Main;

@ContextConfiguration(classes=Main.class, loader=SpringApplicationContextLoader.class)
@IntegrationTest
@WebAppConfiguration
public class SearchSteps extends Factory{
	
	  //Scenario 2
	  @Given("^the client write in the \"([^\"]*)\" the \"([^\"]*)\"$")
	  public void the_client_write_in_the(String option, String type) {
		  System.out.println("the client write in the "+option+" the "+type);
	  }
	  @When("^the client click in search button to execute the search by \"([^\"]*)\"$")
	  public void the_client_click_in_search_button(String str){
		  System.out.println("the client click in search button to execute the search by "+str);
	  }
	  
	  @Then("^search by \"([^\"]*)\" should be successful$")
	  public void search_should_be_successful(String str){
		  System.out.println("search by "+str+" should be successful");
	  }
	  
}

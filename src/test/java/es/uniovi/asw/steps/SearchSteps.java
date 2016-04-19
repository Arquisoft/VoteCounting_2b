package es.uniovi.asw.steps;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Assert;
import org.springframework.web.context.WebApplicationContext;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import es.uniovi.asw.Main;

@ContextConfiguration(classes=Main.class, loader=SpringApplicationContextLoader.class)
@IntegrationTest
@WebAppConfiguration
public class SearchSteps {
	 
	  @Autowired
	  protected WebApplicationContext context;

	  protected MockMvc mvc;
	  protected MvcResult result;
	  
	  @Value("${local.server.port}")
	  protected int port;
	  
	  //Scenario 2
	  @Given("^the client write in the option1 the name")
	  public void the_client_write_in_the_option1() {
		  
	  }
	  @When("^the client click in search button to execute the search by name")
	  public void the_client_click_in_search_button_name(){
	   
	  }
	  
	  @Then("^search by name should be successful")
	  public void search_should_be_successful_name(){
	   
	  }
	  
	  //Scenario 3
	  @Given("^the client write in the option2 the open date")
	  public void the_client_write_in_the_option2() {
	  	
	  }
	  @When("^the client click in search button to execute the search by open date")
	  public void the_client_click_in_search_button_open_date(){
	   
	  }
	  
	  @Then("^search by open date should be successful")
	  public void search_should_be_successful_open_date(){
	   
	  }
	  
	  //Scenario 4
	  @Given("^the client write in the option3 the close date")
	  public void the_client_write_in_the_option3() {
	  	
	  }
	  
	  @When("^the client click in search button to execute the search by close date")
	  public void the_client_click_in_search_button_close_date(){
	   
	  }
	  
	  @Then("^search by close date should be successful")
	  public void search_should_be_successful_close_date(){
	   
	  }
}

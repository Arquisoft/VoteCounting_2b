package es.uniovi.asw.steps;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
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
import es.uniovi.asw.Factory;
import es.uniovi.asw.Main;

@ContextConfiguration(classes=Main.class, loader=SpringApplicationContextLoader.class)
@IntegrationTest
@WebAppConfiguration
public class SearchSteps extends Factory{
	
	  @Autowired
	  protected WebApplicationContext context;

	  protected MockMvc mvc;
	  protected MvcResult result;
	  
	  @Value("${local.server.port}")
	  protected int port;
	  
	  //Scenario 2, 3, 4
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

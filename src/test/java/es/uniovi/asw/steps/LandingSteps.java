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
public class LandingSteps {
  
  @Autowired
  protected WebApplicationContext context;

  protected MockMvc mvc;
  protected MvcResult result;
  
  @Value("${local.server.port}")
  protected int port;

  //Scenario 1
  @When("^the client calls /principal\\.xhtml$$")
  public void the_client_calls() throws Throwable {
    Assert.notNull(context);
    this.mvc = MockMvcBuilders.webAppContextSetup(context).build();
    result = mvc.perform(get("/principal.xhtml")).andReturn();
  }

  @Then("^the client receives status code of (\\d+)$")
  public void the_client_receives_status_code_of(int status) throws Throwable {
    assertThat(result.getResponse().getStatus(), is(status));
  }

  @Then("^the client receives the title \"([^\"]*)\"$")
  public void the_client_receives_the_string_title(String str) throws Throwable {
   assertThat(result.getResponse().getContentAsString(), containsString(str));
  }
  
  @Then("^the client receives the option1 with string \"([^\"]*)\"$")
  public void the_client_receives_the_string_name(String str) throws Throwable {
   assertThat(result.getResponse().getContentAsString(), containsString(str));
  }
  
  @Then("^the client receives the option2 with string \"([^\"]*)\"$")
  public void the_client_receives_the_string_open_date(String str) throws Throwable {
   assertThat(result.getResponse().getContentAsString(), containsString(str));
  }
  
  @Then("^the client receives the option3 with string \"([^\"]*)\"$")
  public void the_client_receives_the_string_close_date(String str) throws Throwable {
   assertThat(result.getResponse().getContentAsString(), containsString(str));
  }
  
  //Scenario 2
  
  @Given("^the client write in the option1 the name")
  public void the_client_write_in_the_option1() {
  	
  }
  
  @Given("^the client write in the option2 the open date")
  public void the_client_write_in_the_option2() {
  	
  }
  
  @Given("^the client write in the option3 the close date")
  public void the_client_write_in_the_option3() {
  	
  }
  
  @When("^the client click in search button")
  public void the_client_click_in_search_button(){
   
  }
  
  @Then("^search should be successful")
  public void search_should_be_successful(){
   
  }
  
}

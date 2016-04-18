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
  
  //Scenario 5
  @When("^the client click on the link \"([^\"]*)\"$")
  public void the_client_click_on_the_link(String str) throws Throwable {
	  System.out.println("the client click on the link "+str);
  }

  @Then("^the client makes call to Lista de Elecciones GET /listaElecciones\\.xhtml$$")
  public void the_client_makes_call_to_Lista_de_Elecciones_GET() throws Throwable {
	  Assert.notNull(context);
	  this.mvc = MockMvcBuilders.webAppContextSetup(context).build();
	  result = mvc.perform(get("/listaElecciones.xhtml")).andReturn();
  }
  
  @Then("^the client receives Lista de Elecciones status code of (\\d+)$")
  public void the_client_receives_Lista_de_Elecciones_status_code_of(int stat) throws Throwable {
	  assertThat(result.getResponse().getStatus(), is(stat));
  }
  
  //Scenario 6
  @When("^the client choose \"([^\"]*)\"$")
  public void the_client_choose(String str) throws Throwable {
	 System.out.println("the client choose "+str);
  }

  @Then("^the client makes call to Referendum GET /listaReferendum\\.xhtml$$")
  public void the_client_makes_call_to_Referendum_GET() throws Throwable {
	  Assert.notNull(context);
	  this.mvc = MockMvcBuilders.webAppContextSetup(context).build();
	  result = mvc.perform(get("/listaReferendum.xhtml")).andReturn();
  }
  
  @Then("^the client receives Referendum status code of (\\d+)$")
  public void the_client_receives_Referendum_status_code_of(int stat) throws Throwable {
	  assertThat(result.getResponse().getStatus(), is(stat));
  }
  
  //Scenario 7
  @Then("^the client makes call to Listas Cerradas GET /listaCerradas\\.xhtml$$")
  public void the_client_makes_call_to_Listas_Cerradas_GET() throws Throwable {
	  Assert.notNull(context);
	  this.mvc = MockMvcBuilders.webAppContextSetup(context).build();
	  result = mvc.perform(get("/listaCerradas.xhtml")).andReturn();
  }
  
  @Then("^the client receives Listas Cerradas status code of (\\d+)$")
  public void the_client_receives_Listas_Cerradas_status_code_of(int stat) throws Throwable {
	  assertThat(result.getResponse().getStatus(), is(stat));
  }
  
  //Scenario 8
  @Then("^the client makes call to Listas Abiertas GET /listaAbiertas\\.xhtml$$")
  public void the_client_makes_call_to_Listas_Abiertas_GET() throws Throwable {
	  Assert.notNull(context);
	  this.mvc = MockMvcBuilders.webAppContextSetup(context).build();
	  result = mvc.perform(get("/listaAbiertas.xhtml")).andReturn();
  }
  //De momento se comprueba en el landing que devuelve 404
  @Then("^the client receives Listas Abiertas status code of (\\d+)$")
  public void the_client_receives_Listas_Abiertas_status_code_of(int stat) throws Throwable {
	  assertThat(result.getResponse().getStatus(), is(stat));
  }
}

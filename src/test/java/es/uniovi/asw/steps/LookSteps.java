package es.uniovi.asw.steps;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.concurrent.TimeUnit;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;
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

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import es.uniovi.asw.Factory;
import es.uniovi.asw.Main;

@ContextConfiguration(classes=Main.class, loader=SpringApplicationContextLoader.class)
@IntegrationTest
@WebAppConfiguration
public class LookSteps extends Factory{
	@Autowired
	  protected WebApplicationContext context;
	  protected MockMvc mvc;
	  protected MvcResult result;
	  private WebDriver driver;
	  private String baseUrl;

	  @Before
	  public void setUp() throws Exception {
	    driver = new FirefoxDriver();
	    baseUrl = "http://localhost:8080";
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  }
	
	
	  //Scenario 5
	  @When("^the client click on the link \"([^\"]*)\"$")
	  public void the_client_click_on_the_link(String str) throws Throwable {
		  System.out.println("the client click on the link "+str);
		  driver.get(baseUrl + "/principal.xhtml");
		  assertEquals(str, driver.findElement(By.linkText(str)).getText());
		  driver.findElement(By.id("j_idt8")).click();
		  
	  }

	  @Then("^the client makes call to Lista Elecciones GET /listaElecciones\\.xhtml$$")
	  public void the_client_makes_call_to_Lista_Elecciones_GET() throws Throwable {
		  Assert.notNull(context);
		  this.mvc = MockMvcBuilders.webAppContextSetup(context).build();
		  result = mvc.perform(get("/listaElecciones.xhtml")).andReturn();
		  driver.get(baseUrl + "/listaElecciones.xhtml");
	  }
	  
	  @Then("^the client receives Lista Elecciones status code of (\\d+)$")
	  public void the_client_receives_Lista_Elecciones_status_code_of(int stat) throws Throwable {
		  assertThat(result.getResponse().getStatus(), is(stat));
	  }
	  
	  //Scenario 6, 7, 8
	  @When("^the client choose \"([^\"]*)\" with id \"([^\"]*)\"$")
	  public void the_client_choose(String str, String id) throws Throwable {
		 System.out.println("the client choose "+str);
		 driver.get(baseUrl + "/principal.xhtml");
		 assertEquals(str, driver.findElement(By.linkText(str)).getText());
		 driver.findElement(By.id("dtLj_idt9")).click();
		 driver.findElement(By.linkText(str)).click();
		 driver.get(baseUrl + "/listaElecciones.xhtml");
		 assertEquals(str, driver.findElement(By.linkText(str)).getText());
		 driver.findElement(By.id("dtLj_idt9")).click();
		 driver.findElement(By.linkText(str)).click();
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
	  
	  @After
	  public void tearDown() throws Exception {
	    driver.quit();
	   
	  }
}

package es.uniovi.asw.steps;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
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

import cucumber.api.PendingException;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import es.uniovi.asw.Main;

@ContextConfiguration(classes = Main.class, loader = SpringApplicationContextLoader.class)
@IntegrationTest
@WebAppConfiguration
public class ReferendumSteps {

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

	@When("^the client click on the link \"([^\"]*)\"$")
	public void the_client_click_on_the_link(String str) throws Throwable {
		System.out.println("the client click on the link " + str);
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

	@Then("^the cliente select a election$")
	public void the_cliente_select_a_election() throws Throwable {
		driver.findElement(By.id("bElection:3:j_idt29:j_idt30")).click();
	}
}

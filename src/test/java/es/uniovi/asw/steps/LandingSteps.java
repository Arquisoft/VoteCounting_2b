package es.uniovi.asw.steps;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
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
import org.springframework.web.context.WebApplicationContext;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Entonces;
import es.uniovi.asw.Factory;
import es.uniovi.asw.Main;

@ContextConfiguration(classes=Main.class, loader=SpringApplicationContextLoader.class)
@IntegrationTest
@WebAppConfiguration
public class LandingSteps{
	protected WebDriver driver;
	protected String baseUrl;
	protected boolean acceptNextAlert = true;
	protected StringBuffer verificationErrors = new StringBuffer();
	
	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		baseUrl = "http://localhost:8080";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	
	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}
  @Autowired
  protected WebApplicationContext context;

  protected MockMvc mvc;
  protected MvcResult result;
  
  @Value("${local.server.port}")
  protected int port;

  //Scenario 1
  @Cuando("^el cliente entra en la web /principal\\.xhtml$$")
  public void el_cliente_entra_en_la_web() throws Throwable {
	  driver.get(baseUrl + "/principal.xhtml");
	  driver.findElement(By.linkText("Principal")).click();
  }

  @Entonces("^el cliente visualiza un mensaje de bienvenida")
  public void el_cliente_visualiza_un_mensaje_de_bienvenida() throws Throwable {
	  driver.get(baseUrl + "/principal.xhtml");
	  assertEquals("Bienvenido a voteCouting_2b", driver.findElement(By.cssSelector("h1")).getText());
	  Factory.textoPresentePagina(driver, "Bienvenido a voteCouting_2b");
  }
  
	

	
  

  
}

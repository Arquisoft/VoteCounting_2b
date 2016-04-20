package es.uniovi.asw.steps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Entonces;
import cucumber.api.java.es.Y;
import es.uniovi.asw.Factory;

public class LookTiposEleccionesSteps {
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
	
	  //Scenario 3, 4, 5
	  @Y("^el cliente selecciona \"([^\"]*)\"$")
	  public void el_cliente_selecciona(String str) throws Throwable {
		  driver.get(baseUrl + "/lista"+str+".xhtml");
		  
		  
	  }

	  @Y("^el cliente ve la tabla de con las respectivas elecciones de tipo \"([^\"]*)\"$")
	  public void el_cliente_ve_la_tabla_de_con_las_respectivas_elecciones_de_tipo(String tipo) throws Throwable {
		  	new Select(driver.findElement(By.name("bElection_length"))).selectByVisibleText("25");
		    new Select(driver.findElement(By.name("bElection_length"))).selectByVisibleText("50");
		    new Select(driver.findElement(By.name("bElection_length"))).selectByVisibleText("100");
		    driver.findElement(By.cssSelector("th.sorting_asc")).click();
		    driver.findElement(By.cssSelector("th.sorting")).click();
		    driver.findElement(By.xpath("//table[@id='bElection']/thead/tr/th[3]")).click();
		    driver.findElement(By.xpath("//table[@id='bElection']/thead/tr/th[4]")).click();
		    driver.findElement(By.xpath("//table[@id='bElection']/thead/tr/th[5]")).click();
		    driver.findElement(By.cssSelector("input.form-control.input-sm")).clear();
	  }
	  
	  @Y("^el cliente elije aquella que sea de su interés")
	  public void el_cliente_elije_aquella_que_sea_de_su_interes() throws Throwable {
//		  driver.findElement(By.id("bElection:1:j_idt29:j_idt30")).click();
//		    new Select(driver.findElement(By.name("bcarPool_length"))).selectByVisibleText("25");
//		    new Select(driver.findElement(By.name("bcarPool_length"))).selectByVisibleText("50");
//		    new Select(driver.findElement(By.name("bcarPool_length"))).selectByVisibleText("100");
//		    driver.findElement(By.cssSelector("th.sorting_asc")).click();
//		    driver.findElement(By.cssSelector("th.sorting")).click();
	  }
}

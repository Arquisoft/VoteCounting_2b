package es.uniovi.asw.steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.es.Entonces;
import cucumber.api.java.es.Y;
import es.uniovi.asw.Factory;
import es.uniovi.asw.Main;

@ContextConfiguration(classes = Main.class, loader = SpringApplicationContextLoader.class)
@IntegrationTest
@WebAppConfiguration
public class LookSteps extends Factory {
	@Autowired
	protected WebApplicationContext context;
	
	private WebDriver driver;
	private String baseUrl;

	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		baseUrl = "http://localhost:8080";
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	// Scenario 5

	@Entonces("^el cliente hace click en \"([^\"]*)\"$")
	public void el_cliente_hace_click_en(String str) throws Throwable {
		driver.get(baseUrl + "/principal.xhtml");
		driver.findElement(By.linkText(str)).click();
	}

	@Y("^el cliente hace click en las diferentes opciones de la tabla")
	public void el_cliente_hace_click_en_las_diferentes_opciones_de_la_tabla() throws Throwable {
		driver.findElement(By.cssSelector("th.sorting_asc")).click();
		driver.findElement(By.cssSelector("th.sorting")).click();
		driver.findElement(By.xpath("//table[@id='bElection']/thead/tr/th[3]")).click();
		driver.findElement(By.xpath("//table[@id='bElection']/thead/tr/th[4]")).click();
		driver.findElement(By.xpath("//table[@id='bElection']/thead/tr/th[5]")).click();
		new Select(driver.findElement(By.name("bElection_length"))).selectByVisibleText("25");
		new Select(driver.findElement(By.name("bElection_length"))).selectByVisibleText("50");
		new Select(driver.findElement(By.name("bElection_length"))).selectByVisibleText("100");
		driver.findElement(By.cssSelector("input.form-control.input-sm")).clear();
	}

	@Y("^selecciona una eleccion de la que quiere saber los resultados")
	public void selecciona_una_eleccion_de_la_que_quiere_saber_los_resultados() throws Throwable {
		driver.findElement(By.cssSelector("th.sorting_asc")).click();
		driver.findElement(By.cssSelector("th.sorting")).click();
		driver.findElement(By.xpath("//table[@id='bElection']/thead/tr/th[3]")).click();
		driver.findElement(By.xpath("//table[@id='bElection']/thead/tr/th[4]")).click();
		driver.findElement(By.xpath("//table[@id='bElection']/thead/tr/th[5]")).click();
		new Select(driver.findElement(By.name("bElection_length"))).selectByVisibleText("25");
		new Select(driver.findElement(By.name("bElection_length"))).selectByVisibleText("50");
		new Select(driver.findElement(By.name("bElection_length"))).selectByVisibleText("100");
		driver.findElement(By.cssSelector("input.form-control.input-sm")).clear();
		driver.findElement(By.cssSelector("input.form-control.input-sm")).sendKeys("cataluña");
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();

	}
}

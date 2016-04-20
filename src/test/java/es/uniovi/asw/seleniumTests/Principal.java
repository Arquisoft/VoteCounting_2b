package es.uniovi.asw.seleniumTests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.openqa.selenium.By;

public class Principal extends Factory{
 
  @Test
  public void testPrincipal() throws Exception {
    driver.get(baseUrl + "/principal.xhtml");
    driver.findElement(By.linkText("Principal")).click();
  }
  @Test
  public void testSeleniumPrincipalTitulos() throws Exception {
    driver.get(baseUrl + "/principal.xhtml");
    assertEquals("Buscar por nombre:", driver.findElement(By.id("j_idt13:j_idt17")).getText());
    assertEquals("Buscar por fecha de inicio:", driver.findElement(By.id("j_idt13:j_idt23")).getText());
    assertEquals("Buscar por fecha de fin:", driver.findElement(By.id("j_idt13:j_idt29")).getText());
    assertEquals("Criterios de búsqueda de un proceso electoral", driver.findElement(By.cssSelector("h4.panel-title")).getText());
    driver.findElement(By.id("j_idt13:j_idt36")).click();
  }
  
  @Test
  public void testSeleniumReferencias() throws Exception {
    driver.get(baseUrl + "/principal.xhtml");
    driver.findElement(By.id("j_idt7")).click();
    driver.findElement(By.id("j_idt8")).click();
    driver.get(baseUrl + "/principal.xhtml");
    driver.findElement(By.id("dtLj_idt9")).click();
    driver.findElement(By.id("j_idt10")).click();
    driver.get(baseUrl + "/principal.xhtml");
    driver.findElement(By.id("dtLj_idt9")).click();
    driver.findElement(By.id("j_idt11")).click();
    driver.get(baseUrl + "/principal.xhtml");
    driver.findElement(By.id("dtLj_idt9")).click();
    driver.findElement(By.id("j_idt12")).click();
  }


}

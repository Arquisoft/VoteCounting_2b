package es.uniovi.asw.seleniumTests;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class TiposEleccionesReferendum extends Factory{

  @Test
  public void testTiposEleccionesReferendum() throws Exception {
    driver.get(baseUrl + "/principal.xhtml");
    driver.findElement(By.id("dtLj_idt9")).click();
    driver.findElement(By.linkText("Referendum")).click();
    new Select(driver.findElement(By.name("bElection_length"))).selectByVisibleText("25");
    new Select(driver.findElement(By.name("bElection_length"))).selectByVisibleText("50");
    new Select(driver.findElement(By.name("bElection_length"))).selectByVisibleText("100");
    driver.findElement(By.cssSelector("th.sorting_asc")).click();
    driver.findElement(By.cssSelector("th.sorting")).click();
    driver.findElement(By.xpath("//table[@id='bElection']/thead/tr/th[3]")).click();
    driver.findElement(By.xpath("//table[@id='bElection']/thead/tr/th[4]")).click();
    driver.findElement(By.xpath("//table[@id='bElection']/thead/tr/th[5]")).click();
    driver.findElement(By.cssSelector("input.form-control.input-sm")).clear();
    driver.findElement(By.cssSelector("input.form-control.input-sm")).sendKeys("Escocia");
    driver.findElement(By.id("bElection:1:j_idt29:j_idt30")).click();
    new Select(driver.findElement(By.name("bcarPool_length"))).selectByVisibleText("25");
    new Select(driver.findElement(By.name("bcarPool_length"))).selectByVisibleText("50");
    new Select(driver.findElement(By.name("bcarPool_length"))).selectByVisibleText("100");
    driver.findElement(By.cssSelector("th.sorting_asc")).click();
    driver.findElement(By.cssSelector("th.sorting")).click();
    driver.findElement(By.cssSelector("input.form-control.input-sm")).clear();
    driver.findElement(By.cssSelector("input.form-control.input-sm")).sendKeys("yes");
  }

}

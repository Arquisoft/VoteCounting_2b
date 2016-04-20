package es.uniovi.asw;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import es.uniovi.asw.seleniumTests.ListaElecciones;
import es.uniovi.asw.seleniumTests.Principal;
import es.uniovi.asw.seleniumTests.TiposEleccionesListasAbiertas;
import es.uniovi.asw.seleniumTests.TiposEleccionesListasCerradas;
import es.uniovi.asw.seleniumTests.TiposEleccionesReferendum;

@RunWith(Suite.class)
@SuiteClasses({ ListaElecciones.class, Principal.class, TiposEleccionesListasAbiertas.class,
		TiposEleccionesListasCerradas.class, TiposEleccionesReferendum.class })
public class TestSuiteSelenium {

}

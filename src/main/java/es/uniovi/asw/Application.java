package es.uniovi.asw;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

@EnableAutoConfiguration
@SpringBootApplication
public class Application implements WebApplicationInitializer //extends SpringBootServletInitializer implements ServletContextInitializer 
{
	public static void main(String[] args)
	{
		SpringApplication app = new SpringApplication(Application.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
		
	}
	
	  @Override
	    public void onStartup(ServletContext container) {
	      XmlWebApplicationContext appContext = new XmlWebApplicationContext();
	      appContext.setConfigLocation("/WEB-INF/web.xml");

	      ServletRegistration.Dynamic dispatcher =
	        container.addServlet("dispatcher", new DispatcherServlet(appContext));
	      dispatcher.setLoadOnStartup(1);
	      dispatcher.addMapping("/");
	    }
	
}
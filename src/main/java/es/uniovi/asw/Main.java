package es.uniovi.asw;

import javax.faces.webapp.FacesServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = { "es.uniovi.asw" })
public class Main extends SpringBootServletInitializer
{
	public static void main(String[] args)
	{
		SpringApplication app = new SpringApplication(Main.class);
		app.run(args);
	}
	
	
	@Bean
	public ServletRegistrationBean facesServletRegistraiton()
	{
		ServletRegistrationBean registration = new ServletRegistrationBean(new FacesServlet(), "*.xhtml");
		
		registration.setName("Faces Servlet");
		registration.setLoadOnStartup(1);
		
		return registration;
	}
	
	
	@Bean
	public ServletContextInitializer servletContextInitializer()
	{
		return servletContext ->
		{
			servletContext.setInitParameter("com.sun.faces.forceLoadConfiguration", Boolean.TRUE.toString());
			servletContext.setInitParameter("javax.faces.FACELETS_SKIP_COMMENTS", Boolean.TRUE.toString());
			servletContext.setInitParameter("BootsFaces_THEME", "cosmo");
		};
	}
	
	
	@Bean
	public WebMvcConfigurerAdapter forwardToIndex()
	{
		return new WebMvcConfigurerAdapter()
		{
			@Override
			public void addViewControllers(ViewControllerRegistry registry)
			{
				registry.addViewController("/").setViewName("redirect:/principal.xhtml");
			}
		};
	}
}
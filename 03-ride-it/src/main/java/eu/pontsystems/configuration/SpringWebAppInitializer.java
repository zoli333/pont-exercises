package eu.pontsystems.configuration;


import java.util.EnumSet;



import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;


public class SpringWebAppInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		 // Create the 'root' Spring application context
	    AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
	    rootContext.register(AppConfiguration.class,WebSecurityConfig.class);

	    // Manage the lifecycle of the root application context
	    servletContext.addListener(new ContextLoaderListener(rootContext));

	    // Create the dispatcher servlet's Spring application context
	    AnnotationConfigWebApplicationContext dispatcherServlet = new AnnotationConfigWebApplicationContext();
	    dispatcherServlet.register(WebConfig.class);

	    // Register and map the dispatcher servlet
	    ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(dispatcherServlet));
	    dispatcher.setLoadOnStartup(1);
	    dispatcher.addMapping("/");

	    // Register spring security FilterChain
	    FilterRegistration.Dynamic registration = servletContext.addFilter("springSecurityFilterChain", DelegatingFilterProxy.class);
	    EnumSet<DispatcherType> dispatcherTypes = EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.ERROR, DispatcherType.ASYNC);
	    registration.addMappingForUrlPatterns(dispatcherTypes, true, "/*");
		
	}
	
}

package eu.pontsystems.configuration;

import javax.sql.DataSource;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;



@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  
	@Autowired
	@Qualifier("driverPassengerDetailsService")
	private UserDetailsService userDetailsService;
	
	@Autowired
	SecurityHandler securityHandler;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		System.out.println("security config called!!!!");
	    http
	    .csrf().disable()
	    .authorizeRequests()
	    .antMatchers("/","/login**").permitAll()
	    .antMatchers("/driverPage").access("hasRole('DRIVER')")
	    .antMatchers("/passengerPage").access("hasRole('PASSENGER')")
	    .and()
	    .formLogin().loginPage("/login")
	    .failureUrl("/login?error")
	    .successHandler(securityHandler)
	    .usernameParameter("username").passwordParameter("password")
	    .permitAll();
	}
  
  
}

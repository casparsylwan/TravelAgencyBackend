package se.sylwan.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import se.sylwan.security.CustomerDetailsService;

@EnableWebSecurity
public class SecurityConfigurer extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private CustomerDetailsService customerDetailsService;
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.userDetailsService(customerDetailsService);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return NoOpPasswordEncoder.getInstance();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception 
	{
		http.csrf().disable()
			.authorizeRequests().antMatchers("/api/v1/authenticate", "/api/v1/ping", "/api/v1/customer/new").permitAll()
			.antMatchers("/api/v1/customer/**").hasAnyRole("ADMIN", "CUSTOMER")
			.anyRequest().authenticated();
//			.and()
//			.authorizeRequests().antMatchers("/api/v1/customer/**").hasAnyRole("ADMIN", "CUSTOMER")
//			.anyRequest().authenticated();
		
	}
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception
	{
		return super.authenticationManagerBean();
	}
//		System.out.println("HTTPSEC");
//		http.authorizeRequests()
//			.antMatchers("/admin").hasRole("ADMIN")
//			.antMatchers("/customer/**").hasAnyRole("ADMIN", "CUSTOMER")
//			.antMatchers("/**").hasRole("ADMIN")
//			.and().formLogin();
//		
//	}
	
	

}

package com.example.demo.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.Md4PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

@EnableWebSecurity
@Configuration 
 public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource dataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		//auth.inMemoryAuthentication().withUser("admin").password("1234").roles("USER", "ADMIN");
		auth.jdbcAuthentication().dataSource(dataSource)
		.usersByUsernameQuery("select login as principal, pass as credentials, active from depute where login = ?")
		.authoritiesByUsernameQuery("select login as principal, role as role from privillege_depute where login = ?")
		.passwordEncoder(new BCryptPasswordEncoder())
		.rolePrefix("ROLE_");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.formLogin().loginPage("/login");
		http.authorizeRequests().antMatchers("/president/*").hasRole("president");
		http.authorizeRequests().antMatchers("/secretaire/*").hasRole("secretaire");
		http.authorizeRequests().antMatchers("/depute/*").hasRole("depute");
		http.exceptionHandling().accessDeniedPage("/403");
	}
	
}
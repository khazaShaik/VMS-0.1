package com.ccil.vms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailsService	userDetailsService;
	@Autowired
	CustomSuccessHandler		customSuccessHandler;
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http	.authorizeRequests()
				.antMatchers("/resources/**","/assets/**","/registration", "/welcome", "/emailVerify", "/emailRegitrationConfirm").permitAll()
				.antMatchers("/user/**").access("hasRole('USER')")
				.antMatchers("/admin/**").access("hasRole('ADMIN')")
				.antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')")
				.antMatchers("/code").access("hasRole('PRE_AUTH_USER')")
				.anyRequest().fullyAuthenticated()
				.and()
				.formLogin()
				.loginPage("/login").successHandler(customSuccessHandler)
				.usernameParameter("username")
				.passwordParameter("password") 	
				.permitAll()
				.and()
				.logout()
				.permitAll()
				.and().exceptionHandling();
		// System.out.println("----------Inside SecurityConfig-------------");
		// http.authorizeRequests()
		// .antMatchers("/", "/home").access("hasRole('USER')")
		// .antMatchers("/admin/**").access("hasRole('ADMIN')")
		// .antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')")
		// .and().formLogin() //login configuration
		// .loginPage("/login").successHandler(customSuccessHandler)
		// .usernameParameter("username")
		// .passwordParameter("password")
		// .defaultSuccessUrl("/user/home")
		// .and().logout() //logout configuration
		// .logoutUrl("/appLogout")
		// .logoutSuccessUrl("/login.jsp")
		// .and().exceptionHandling() //exception handling configuration
		// .accessDeniedPage("/Access_Denied");
	}
	// password encription
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}
	
}

package com.nnk.springboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.server.authentication.logout.DelegatingServerLogoutHandler;
import org.springframework.security.web.server.authentication.logout.SecurityContextServerLogoutHandler;
import org.springframework.security.web.server.authentication.logout.WebSessionServerLogoutHandler;
import org.springframework.security.web.server.header.XFrameOptionsServerHttpHeadersWriter.Mode;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	/*
	 * Customize filters to secure app
	 */
	@SuppressWarnings("removal")
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		/*DelegatingServerLogoutHandler logoutHandler = new DelegatingServerLogoutHandler(
		            new WebSessionServerLogoutHandler(), new SecurityContextServerLogoutHandler()
		    );*/

		http

				.cors(AbstractHttpConfigurer::disable).csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(
						(authorize) -> authorize
						.requestMatchers("/home", "/", "/user/list","/user/validate","/user/add").permitAll()	
						.requestMatchers("/resources/**").permitAll()
						.anyRequest().authenticated())
				.httpBasic(Customizer.withDefaults())
				
				.formLogin()
				.defaultSuccessUrl("/bidList/list")
				
				/*
				 * form -> form
						.loginPage("/app/login")
						//.defaultSuccessUrl("/bidList/add")
						 .failureUrl("/app/login.html?error=true")
						.permitAll()
				 */
						//.logoutSuccessUrl("/login"))
				//.exceptionHandling(exceptionHandling -> exceptionHandling.accessDeniedPage("/app/error"))
;
		return http.build();
	}
	
	

	/*
	 * User authentication from the database
	 */
	@Bean
	public AuthenticationManager authenticationManager(BCryptPasswordEncoder passwordEncoder) {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(customUserDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder);

		return new ProviderManager(authenticationProvider);
	}

	/*
	 * Password decryption
	 */
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
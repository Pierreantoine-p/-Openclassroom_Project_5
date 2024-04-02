package com.nnk.springboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
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
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.server.authentication.logout.DelegatingServerLogoutHandler;
import org.springframework.security.web.server.authentication.logout.SecurityContextServerLogoutHandler;
import org.springframework.security.web.server.authentication.logout.WebSessionServerLogoutHandler;
import org.springframework.security.web.server.header.XFrameOptionsServerHttpHeadersWriter.Mode;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

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
		http

		.cors(AbstractHttpConfigurer::disable).csrf(AbstractHttpConfigurer::disable)
		.authorizeHttpRequests(
				(authorize) -> authorize
				.requestMatchers("/home", "/", "/user/list","/user/add", "/user/validate").permitAll()	
				.requestMatchers("/css/**").permitAll()
				.anyRequest().authenticated())
		

		
		
		.formLogin((formLogin) -> formLogin
				.defaultSuccessUrl("/bidList/list", true)
			    .failureUrl("/login-error")
                .failureHandler(authenticationFailureHandler()) 
                )
		.logout((logout) -> logout.permitAll()
				.logoutRequestMatcher(new AntPathRequestMatcher("/app-logout"))
				.logoutSuccessUrl("/"))
		.exceptionHandling((exceptionHandling) -> exceptionHandling.accessDeniedPage("/app/error"));
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
		System.out.println("authenticationProvider : " + authenticationProvider);
		return new ProviderManager(authenticationProvider);
	}	

	/*
	 * Password decryption
	 */
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new SimpleUrlAuthenticationFailureHandler();
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        source.setBasename("messages"); // Nom du fichier de messages sans l'extension
        source.setDefaultEncoding("UTF-8");
        return source;
    }
}
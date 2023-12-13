package com.nnk.springboot.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {


	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
		
		.cors(AbstractHttpConfigurer::disable)
		.csrf(AbstractHttpConfigurer::disable)
		.authorizeHttpRequests((authorize) -> authorize
				.requestMatchers("/home").permitAll()
				.requestMatchers("/app/login").permitAll()
				.requestMatchers("/").permitAll()
				//.requestMatchers("/user/list").hasRole("ADMIN")
				.anyRequest().authenticated()
				)
		.httpBasic(Customizer.withDefaults())
		.formLogin(Customizer.withDefaults())
		.defaultSuccessUrl("/bidList/list")
        .and().logout()
        .logoutUrl("/app-logout")
        .logoutSuccessUrl("/");

		return http.build();
	}

	
	@Bean
	public UserDetailsServiczze userDetailsService() {
		UserDetails user = User.builder()
				.username("user")
				.password("")
				.roles("USER")
				.build();
		UserDetails admin = User.builder()
				.username("admin")
				.password(passwordEncoder().encode("admin"))
				.roles("USER", "ADMIN")
				.build();

		return new InMemoryUserDetailsManager(user, admin);
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
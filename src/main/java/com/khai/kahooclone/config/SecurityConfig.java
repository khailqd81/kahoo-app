package com.khai.kahooclone.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import com.khai.kahooclone.filter.CustomAuthenicationFilter;
import com.khai.kahooclone.filter.CustomAuthorizationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		CustomAuthenicationFilter authenicationFilter = 
				new CustomAuthenicationFilter(
						authenticationManager(http.getSharedObject(AuthenticationConfiguration.class)));
		authenicationFilter.setFilterProcessesUrl("/api/login");
		
		http.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues());
		
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.csrf().disable();
		
		http.authorizeHttpRequests()
			.antMatchers("/api/login").permitAll()
			.antMatchers("/user/**").hasAnyAuthority("Teacher");
		//http.authorizeHttpRequests().anyRequest().authenticated();
		
		http.addFilter(authenicationFilter);
		http.addFilterBefore(
				new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

	
//	@Bean
//	public AuthenticationManager authManager(HttpSecurity http) 
//	  throws Exception {
//	    return http.getSharedObject(AuthenticationManagerBuilder.class)
//	      .userDetailsService(userDetailsService)
//	      .passwordEncoder(bCryptPasswordEncoder)
//	      .and()
//	      .build();
//	}
	
	@Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception { 
        return authenticationConfiguration.getAuthenticationManager();
    }

}

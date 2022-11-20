package com.api.chess.management.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.api.chess.management.constants.ConfigurationConstants;
import com.api.chess.management.constants.SecurityConstants;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		
		 final String[] AUTH_WHITELIST = {
		            "/v2/api-docs",
		            "/swagger-resources",
		            "/swagger-resources/**",
		            "/configuration/ui",
		            "/configuration/security",
		            "/swagger-ui.html",
		            "/webjars/**",
		            ConfigurationConstants.AUTHENTICATION_URL
		    };
		 
		 final String[] LIST_URLS = {
				 ConfigurationConstants.COUNTRY_API_URL,
				 ConfigurationConstants.GAME_API_URL,
				 ConfigurationConstants.OPENNING_API_URL,
				 ConfigurationConstants.PLAYER_API_URL,
				 ConfigurationConstants.RESULT_API_URL 
		 };
		
		httpSecurity
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and().cors()
			.and().csrf().disable()
			.authorizeRequests()
			
			//ALL PERMISSIONS
			.antMatchers(AUTH_WHITELIST).permitAll()
			
			//ADMIN PERMISSIONS
			.antMatchers(ConfigurationConstants.USER_API_URL + "/**").hasAuthority(SecurityConstants.ROL_ADMIN)
			
			//READ-LIST PERMISSIONS
			.antMatchers(HttpMethod.POST, LIST_URLS).hasAuthority(SecurityConstants.ROL_READ_WRITE)
			.antMatchers(HttpMethod.PUT, LIST_URLS).hasAuthority(SecurityConstants.ROL_READ_WRITE)
			.antMatchers(HttpMethod.DELETE, LIST_URLS).hasAuthority(SecurityConstants.ROL_READ_WRITE)
			.antMatchers(HttpMethod.GET, LIST_URLS).hasAuthority(SecurityConstants.ROL_READ_WRITE)
			
			//READ PERMISSIONS
			.antMatchers(HttpMethod.GET, LIST_URLS).hasAuthority(SecurityConstants.ROL_READ)

			.and()
			.addFilter(new JWTAuthenticationFilter(authenticationManager()))
			.addFilter(new JWTAuthorizationFilter(authenticationManager()))
			;
		

	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		// Se define la clase que recupera los usuarios y el algoritmo para procesar las passwords
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
		return source;
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}

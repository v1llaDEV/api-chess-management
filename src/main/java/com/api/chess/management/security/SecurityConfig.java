package com.api.chess.management.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.api.chess.management.constants.ConfigurationConstants;
import com.api.chess.management.constants.SecurityConstants;

// TODO: Auto-generated Javadoc
/**
 * The Class SecurityConfig.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig{

	/** The user details service. */
	@Autowired
	private UserDetailsService userDetailsService;
	
	/** The security token. */
	@Autowired
	private SecurityToken securityToken;
	
	/** The b crypt password encoder. */
	@Autowired 
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	/**
	 * Filter chain.
	 *
	 * @param httpSecurity the http security
	 * @return the security filter chain
	 * @throws Exception the exception
	 */
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		 final String ALL_RESOURCES_MATCHER = "/**"; 
		
		
		 final String[] AUTH_WHITELIST = {
		            "/v2/api-docs",
		            "/swagger-resources",
		            "/swagger-resources/**",
		            "/configuration/ui",
		            "/configuration/security",
		            "/swagger-ui.html",
		            "/webjars/**",
		            ConfigurationConstants.AUTHENTICATION_URL + ALL_RESOURCES_MATCHER
		    };
		 
		 final String[] LIST_URLS = {
			 ConfigurationConstants.COUNTRY_API_URL + ALL_RESOURCES_MATCHER,
			 ConfigurationConstants.GAME_API_URL + ALL_RESOURCES_MATCHER,
			 ConfigurationConstants.OPENNING_API_URL + ALL_RESOURCES_MATCHER,
			 ConfigurationConstants.PLAYER_API_URL + ALL_RESOURCES_MATCHER,
			 ConfigurationConstants.RESULT_API_URL + ALL_RESOURCES_MATCHER
		 };
		
		 httpSecurity.cors();
		 httpSecurity.csrf().disable();
		 httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
			
		 /** USUARIOS Y SWAGGER */
		 httpSecurity.authorizeRequests().antMatchers(AUTH_WHITELIST).permitAll();
		 
		//USERS ROUTES
		 httpSecurity.authorizeRequests().antMatchers(ConfigurationConstants.USER_API_URL + ALL_RESOURCES_MATCHER).hasAnyAuthority(SecurityConstants.ROL_ADMIN);
		 
		//COUNTRIES, GAMES, OPENNINGS, PLAYERS, RESULTS ROUTES
		 httpSecurity.authorizeRequests().antMatchers(HttpMethod.POST, LIST_URLS).hasAnyAuthority(SecurityConstants.ROL_ADMIN, SecurityConstants.ROL_READ_WRITE);
		 httpSecurity.authorizeRequests().antMatchers(HttpMethod.PUT, LIST_URLS).hasAnyAuthority(SecurityConstants.ROL_ADMIN, SecurityConstants.ROL_READ_WRITE);
		 httpSecurity.authorizeRequests().antMatchers(HttpMethod.DELETE, LIST_URLS).hasAnyAuthority(SecurityConstants.ROL_ADMIN, SecurityConstants.ROL_READ_WRITE);
		 httpSecurity.authorizeRequests().antMatchers(HttpMethod.GET, LIST_URLS).hasAnyAuthority(SecurityConstants.ROL_ADMIN, SecurityConstants.ROL_READ_WRITE, SecurityConstants.ROL_READ);

		 httpSecurity.addFilterBefore(new JWTAuthorizationFilter(securityToken), UsernamePasswordAuthenticationFilter.class);
		 return httpSecurity.build();

	}

	/**
     * Authentication manager authentication manager.
     *
     * @return the authentication manager
     * @throws Exception the exception
     */
    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return new ProviderManager(daoAuthenticationProvider());
    }
	
	/**
	 * Cors configuration source.
	 *
	 * @return the cors configuration source
	 */
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowedOrigins(List.of("http://143.47.34.80:8100"));
		source.registerCorsConfiguration("/**", corsConfiguration);
		return source;
	}
	
	 /**
     * Dao authentication provider dao authentication provider.
     *
     * @return the dao authentication provider
     */
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder);
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        return daoAuthenticationProvider;
    }

	

}

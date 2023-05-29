package com.api.chess.management.configuration;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

// TODO: Auto-generated Javadoc
/**
 * The Class SwaggerConfig.
 *
 * @author v1llaDEV
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	/**
	 * Api docket.
	 *
	 * @return the docket
	 */
	@Bean
	public Docket apiDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.securityContexts(Arrays.asList(securityContext()))
			      .securitySchemes(Arrays.asList(apiKey()))
				.apiInfo(getApiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.api.chess.management.controller"))
				.paths(PathSelectors.any())
				.build();
	}
	
	
	/**
	 * Gets the api info.
	 *
	 * @return the api info
	 */
	private ApiInfo getApiInfo() {
		return new ApiInfo(
				"Chess management API",
				"Chess management  API Description",
				"1.0",
				"",
				new Contact("", "", ""),
				"LICENSE",
				"LICENSE URL",
				Collections.emptyList()
				);
	}
	
	/**
	 * Api key.
	 *
	 * @return the api key
	 */
	private ApiKey apiKey() { 
	    return new ApiKey("JWT", "Authorization", "header"); 
	}
	
	/**
	 * Security context.
	 *
	 * @return the security context
	 */
	private SecurityContext securityContext() { 
	    return SecurityContext.builder().securityReferences(defaultAuth()).build(); 
	} 
	
	/**
	 * Default auth.
	 *
	 * @return the list
	 */
	private List<SecurityReference> defaultAuth() { 
	    AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything"); 
	    AuthorizationScope[] authorizationScopes = new AuthorizationScope[1]; 
	    authorizationScopes[0] = authorizationScope; 
	    return Arrays.asList(new SecurityReference("JWT", authorizationScopes)); 
	}
}
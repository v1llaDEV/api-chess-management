package com.api.chess.management.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "spring.datasource")
@Data
public class PostgresBBDDConfig {

	private String username;
	
	private String password;
}


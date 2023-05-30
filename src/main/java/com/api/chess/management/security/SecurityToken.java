package com.api.chess.management.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

// TODO: Auto-generated Javadoc
/**
 * The Class SecurityToken.
 */
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "jwt")

/**
 * Instantiates a new security token.
 */
@Data
public class SecurityToken {

    /** The key. */
    private String key;

    
}

package com.api.chess.management.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import com.api.chess.management.constants.SecurityConstants;

import io.jsonwebtoken.Jwts;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
	
	

	public JWTAuthorizationFilter(AuthenticationManager authManager) {
		super(authManager);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		String header = req.getHeader(SecurityConstants.HEADER_AUTHORIZACION_KEY);
		if (header == null || !header.startsWith(SecurityConstants.TOKEN_BEARER_PREFIX)) {
			chain.doFilter(req, res);
			return;
		}
		UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(req, res);
	}

	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(SecurityConstants.HEADER_AUTHORIZACION_KEY);
		if (token != null) {
			// Se procesa el token y se recupera el usuario.
			String user = Jwts.parser()
						.setSigningKey(SecurityConstants.JWT_SECRET_KEY_PROPERTY_NAME)
						.parseClaimsJws(token.replace(SecurityConstants.TOKEN_BEARER_PREFIX, ""))
						.getBody()
						.getSubject();
			
			ArrayList<HashMap<String, String>> roles  =  (ArrayList<HashMap<String, String>>) Jwts.parser()
					.setSigningKey(SecurityConstants.JWT_SECRET_KEY_PROPERTY_NAME)
					.parseClaimsJws(token.replace(SecurityConstants.TOKEN_BEARER_PREFIX, ""))
					.getBody()
					.get("roles");
			
			List<GrantedAuthority> rolList = new ArrayList<GrantedAuthority>();
			
			for(HashMap<String, String> rol : roles) {
				rolList.add(new SimpleGrantedAuthority((String) rol.get("authority")));
			}

			if (user != null) {
				return new UsernamePasswordAuthenticationToken(user, null, rolList);
			}
			return null;
		}
		return null;
	}
}

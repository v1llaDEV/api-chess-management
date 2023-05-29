package com.api.chess.management.dto.responses;

import java.util.Date;
import java.util.List;

import com.api.chess.management.entity.Rol;

import lombok.Data;

@Data
public class UserResponse {
	
	private String username;
	
	private Date created;
	
	private List<Rol> roles;
}

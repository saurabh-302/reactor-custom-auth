package com.saurabh.reactorsample.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebSession;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.saurabh.reactorsample.model.RoleDTO;
import com.saurabh.reactorsample.model.UserAuthentication;
import com.saurabh.reactorsample.model.UserDTO;

import reactor.core.publisher.Mono;

@RestController
public class Controller {

	
	@GetMapping("/login")
	public Mono<SecurityContextImpl> greet(WebSession webSession, @RequestParam String userName,
			@RequestParam String password) {
		if ("user".equals(userName) && "password".equals(password)) {
			Set<GrantedAuthority> permissionsForRole1 = new HashSet<>();
            permissionsForRole1.add(new SimpleGrantedAuthority("ROLE_PERMISSION1"));

            Set<RoleDTO> userRoles = new HashSet<>();
            userRoles.add(new RoleDTO("BASIC_ROLE_1", permissionsForRole1));

            UserDTO user = new UserDTO("user-name", userRoles);
            Authentication userAuthentication = new UserAuthentication(user);
			SecurityContextImpl secureContext = new SecurityContextImpl();
			secureContext.setAuthentication(userAuthentication);
			webSession.getAttributes().put("SPRING_SECURITY_CONTEXT", secureContext);
			
			return Mono.just(secureContext);
		} else {
			throw new RuntimeException("Bad Credentials");
		}
		
	}
	
	@GetMapping("/context")
	public Mono<SecurityContextImpl> greet1(WebSession webSession,ServerWebExchange serverWebExchange) throws JsonProcessingException {
		SecurityContextImpl obj=(SecurityContextImpl)webSession.getAttributes().get("SPRING_SECURITY_CONTEXT");
		return Mono.just(obj);
	}
}

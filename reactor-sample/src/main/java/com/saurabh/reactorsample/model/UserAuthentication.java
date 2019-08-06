package com.saurabh.reactorsample.model;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserAuthentication implements Authentication {

	private static final long serialVersionUID = 1L;
	private Set<GrantedAuthority> authorities = Collections.unmodifiableSet(new HashSet<>());
	private UserDTO details;
	private boolean authenticated;
	
	public UserAuthentication() {
		super();
		this.authenticated = true;
	}

	public UserAuthentication(UserDTO user) {
		setDetails(user);
		this.authenticated = true;
	}

	public void setAuthorities(Set<GrantedAuthority> authorities) {
		this.authorities = Collections.unmodifiableSet(authorities);
	}

	public void setDetails(UserDTO user) {
		this.details = user;
		Set<GrantedAuthority> authorities = new HashSet<>();
		user.getRoles().forEach(role -> authorities.addAll(role.getPermissions()));
		this.authorities = Collections.unmodifiableSet(authorities);
	}

	@Override
	public String getName() {
		return null;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public Object getCredentials() {
		return null;
	}

	@Override
	public Object getDetails() {
		return details;
	}

	@Override
	@JsonIgnore
	public Object getPrincipal() {
		return details.getName();
	}

	@Override
	public boolean isAuthenticated() {
		return authenticated;
	}

	@Override
	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}

}
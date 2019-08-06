package com.saurabh.reactorsample.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.Set;

public class UserDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name;
	private Set<RoleDTO> roles;

	public UserDTO(String name, Set<RoleDTO> roles) {
		this.setName(name);
		this.roles = Collections.unmodifiableSet(roles);
	}

	public Set<RoleDTO> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleDTO> roles) {
		this.roles = roles;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "UserDTO [name=" + name + ", roles=" + roles + "]";
	}

}

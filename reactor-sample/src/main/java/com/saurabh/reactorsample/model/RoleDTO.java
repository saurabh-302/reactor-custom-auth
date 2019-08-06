package com.saurabh.reactorsample.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;

public class RoleDTO implements Serializable {

    private static final long serialVersionUID = 2L;
    private String roleName;
    private Set<GrantedAuthority> permissions;

    public RoleDTO(String roleName, Set<GrantedAuthority> permissions) {
        this.roleName = roleName;
        if(permissions==null) {
        this.permissions = Collections.unmodifiableSet(new HashSet<>());
        }else {
        	this.permissions = Collections.unmodifiableSet(permissions);
        }
    }

    public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public void setPermissions(Set<GrantedAuthority> permissions) {
		this.permissions = Collections.unmodifiableSet(permissions);
	}

	public String getRoleName() {
        return roleName;
    }

    public Set<GrantedAuthority> getPermissions() {
        return permissions;
    }

    @Override
    public boolean equals(Object role) {
        if (role != null && (role.getClass() == this.getClass())) {
            return roleName.equals(((RoleDTO) role).getRoleName());
        }
        return roleName.equals(role);
    }

    @Override
    public int hashCode() {
        return roleName.hashCode();
    }

    @Override
    public String toString() {
        return "RoleDTO [roleName=" + roleName + ", permissions=" + permissions + "]";
    }
}
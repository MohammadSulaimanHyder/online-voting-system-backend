package com.app.votingsystem.models;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetialsImpl implements UserDetails {
	
	private String userName;
	private String password;
	private String voterId;
	private Boolean isEnabled;
	private List<GrantedAuthority> authorities;
	
	
	public UserDetialsImpl() {
		super();
	}

	public UserDetialsImpl(Voter voter, String role) {
		super();
		this.userName = voter.getFirstName() + " " + voter.getLastName();
		this.password = voter.getPassword();
		this.voterId = voter.getVoterId();
		this.isEnabled = voter.getEnabled();
		this.authorities = Arrays.stream(role.split(","))
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		List<GrantedAuthority> ls = new ArrayList<GrantedAuthority>();
		ls.add(new SimpleGrantedAuthority("USER"));
		
		return ls;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return userName;
	}
	
	public String getVoterId() {
		return voterId;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
			return isEnabled;
	}

}

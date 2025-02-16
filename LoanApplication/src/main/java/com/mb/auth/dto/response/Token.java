package com.mb.auth.dto.response;

public class Token {
	private String jwt;

	public Token(String jwt) {
		this.jwt = jwt;
	}

	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}
}

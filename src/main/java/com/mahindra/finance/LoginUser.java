package com.mahindra.finance;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class LoginUser {

	private String email;
	private String password;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean checkPassword(String password) {
        // Check if the provided password matches the stored hashed password
        return BCrypt.checkpw(password, this.password);
    }
	
	@Override
	public String toString() {
		return "LoginUser [email=" + email + ", password=" + password + "]";
	}
	
}

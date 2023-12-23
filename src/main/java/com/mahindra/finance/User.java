package com.mahindra.finance;

import org.springframework.security.crypto.bcrypt.BCrypt;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_details")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String email; 
	private String password;
	
	public User() {
		super();
	}
	
	public User(Long id, String name, String email, String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
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
		this.password = BCrypt.hashpw(password, BCrypt.gensalt());
	}
	
	public boolean checkPassword(String password) {
        // Check if the provided password matches the stored hashed password
        return BCrypt.checkpw(password, this.password);
    }
	
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + "]";
	}
	
}

package com.mahindra.finance;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Advisor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id; 
	private String advisorName; 
	private String photoURL;
	
	public Advisor() {
		super();
	}
	public Advisor(Long id, String advisorName, String photoURL) {
		super();
		this.id = id;
		this.advisorName = advisorName;
		this.photoURL = photoURL;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAdvisorName() {
		return advisorName;
	}
	public void setAdvisorName(String advisorName) {
		this.advisorName = advisorName;
	}
	public String getPhotoURL() {
		return photoURL;
	}
	public void setPhotoURL(String photoURL) {
		this.photoURL = photoURL;
	}
	@Override
	public String toString() {
		return "Advisor [id=" + id + ", advisorName=" + advisorName + ", photoURL=" + photoURL + "]";
	}
	
}

package com.mahindra.finance.jsonwebtoken.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {

	private String userId; 
	private String name; 
	private String email; 

	
	
}

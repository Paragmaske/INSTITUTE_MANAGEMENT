package com.institute.Model;

import jakarta.persistence.Entity; 
import jakarta.persistence.GeneratedValue; 
import jakarta.persistence.GenerationType; 
import jakarta.persistence.Id; 
import lombok.AllArgsConstructor; 
import lombok.Data; 
import lombok.NoArgsConstructor;
import lombok.ToString; 

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserInfo { 

	@Id
	
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int id; 
	private String name; 
	private String email; 
	private String password; 
	private String roles; 

} 

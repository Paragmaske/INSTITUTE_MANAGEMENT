package com.institute.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Institute {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long instituteId;
	

	String instituteName;
	
	String location;
	
	 @NotNull(message="phone no cannot be null")
	 @Digits(integer = 10, fraction = 0, message = "Phone number must be a 10-digit number")
	 Long phoneNumber;
	
}

package com.example.firstapp.modell;

import org.springframework.cloud.gcp.data.datastore.core.mapping.Entity;
import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class SignUp{
	
	@Id
	private String email;
	private String name;
	private Long phone;
	private String password;

}
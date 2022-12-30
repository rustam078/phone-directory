package com.example.firstapp.modell;


import java.util.List;
import java.util.Set;

import org.springframework.cloud.gcp.data.datastore.core.mapping.Entity;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Contact {

@Id
Long contactid;
String name;
String address;

@Transient
List<Phone> phone;
@Transient
List<Email> emails;
private String useremail;
String category;
	
}

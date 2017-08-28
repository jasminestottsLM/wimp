package com.liberymutual.goforcode.wip.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Actor {

	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(length=75, nullable=false)
	private String firstName;
	
	@Column(length=75, nullable=true)
	private String lastname;
	
	@Column(nullable=true)
	private Long activeSinceYear;
	
	@Column(nullable=true)
	private Date birthDate;
	
}

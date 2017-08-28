package com.liberymutual.goforcode.wip.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Movie {

	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(length=300, nullable=false)
	private String title;
	
	@Column(nullable=true)
	private Date releaseDate;
	
	@Column(nullable=true)
	private Long budget;
	
	@Column(length=500, nullable=false)
	private String distributor;
}

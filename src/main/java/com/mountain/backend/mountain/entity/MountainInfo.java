package com.mountain.backend.mountain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jdk.jfr.Name;

@Entity
public class MountainInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
}

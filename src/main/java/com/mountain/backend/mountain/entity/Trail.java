package com.mountain.backend.mountain.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jdk.jfr.Name;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Trail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Name("trailId")
	private Long id;

	private String trailName;
	private String trailMainName;

	@ManyToOne
	@JoinColumn(name = "mountain_id")
	private Mountain mountain;

	@OneToMany(mappedBy = "trail", cascade = CascadeType.ALL)
	private List<Coordinate> coordinates;
}

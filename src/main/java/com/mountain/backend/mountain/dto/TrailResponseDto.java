package com.mountain.backend.mountain.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mountain.backend.mountain.entity.Coordinate;
import com.mountain.backend.mountain.entity.Trail;

import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TrailResponseDto {
	private Long trailsId;
	private String trailName;

	private List<CoordinateResponseDto> coordinateDtoList;

	public TrailResponseDto(Trail trail){
		this.trailsId = trail.getId();
		this.trailName = trail.getTrailName();
		List<Coordinate> coordinates = trail.getCoordinates();
		List<CoordinateResponseDto> cDtos = new ArrayList<>();
		for (Coordinate coordinate : coordinates) {
			CoordinateResponseDto coordinateResponseDto = new CoordinateResponseDto(coordinate);
			cDtos.add(coordinateResponseDto);
		}
		this.coordinateDtoList = cDtos;


	}
}

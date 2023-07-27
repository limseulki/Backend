package com.mountain.backend.mountain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mountain.backend.mountain.entity.Coordinate;

import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CoordinateResponseDto {

	private Double lat;
	private Double lon;

	public CoordinateResponseDto(Coordinate coordinate){
		this.lat = coordinate.getLat();
		this.lon = coordinate.getLon();

	}
}

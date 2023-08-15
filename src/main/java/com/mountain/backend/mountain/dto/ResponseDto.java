package com.mountain.backend.mountain.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mountain.backend.mountain.entity.Coordinate;
import com.mountain.backend.mountain.entity.Mountain;
import com.mountain.backend.mountain.entity.Trail;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDto {
	private String mountainName;        // 산이름
	private String mountainAddress;     // 산주소
	private String mountainInfo;        // 산정보
	private Integer mountainHeight;		// 산높이
	private List<TrailResponseDto> trailsList;
	private List<Coordinate> coordinates;

	public ResponseDto(Mountain mountain){

		this.mountainName = mountain.getMountainName();
		this.mountainAddress = mountain.getMountainAddress();
		this.mountainInfo = mountain.getMountainInfo();
		this.mountainHeight = mountain.getMountainHeight();

		List<Trail> trails = mountain.getTrails();
		List<TrailResponseDto> cDtos = new ArrayList<>();//객체들을 저장할 ArrayList를 생성
		for (Trail trail : trails) {
			TrailResponseDto trailResponseDto = new TrailResponseDto(trail);
			cDtos.add(trailResponseDto);
		}
		this.trailsList = cDtos;



	}

}

package com.mountain.backend.mountain.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mountain.backend.common.Message.Message;
import com.mountain.backend.mountain.dto.ResponseDto;
import com.mountain.backend.mountain.dto.SearchRequestDto;
import com.mountain.backend.mountain.entity.Coordinate;
import com.mountain.backend.mountain.entity.Mountain;
import com.mountain.backend.mountain.entity.Trail;
import com.mountain.backend.mountain.repository.CoordinateRepository;
import com.mountain.backend.mountain.repository.MountainRepository;
import com.mountain.backend.mountain.repository.TrailRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TestService {

	private final MountainRepository mountainRepository;
	private final TrailRepository trailRepository;
	private final CoordinateRepository coordinateRepository;

	@Transactional
	public ResponseEntity<Message> test(SearchRequestDto requestDto){
		String keyword = requestDto.getKeyword();
		Mountain mountain = mountainRepository.findByMountainName(keyword);
		List<Trail> trails = mountain.getTrails();
		for (Trail trail : trails) {
			List<Coordinate> coordinates = trail.getCoordinates();
		}
		ResponseDto responseDto = new ResponseDto(mountain);

		// return ResponseEntity.ok(new Message(responseDto));
		return null;


	}

}

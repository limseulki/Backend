package com.mountain.backend.mountain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class SearchRequestDto {
	private String keyword;
}

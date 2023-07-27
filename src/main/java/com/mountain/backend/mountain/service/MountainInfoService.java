package com.mountain.backend.mountain.service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;

@Service
public class MountainInfoService {

	// @Value("${api.serviceKey}")
	private String encodedServiceKey;

	public String getForestData(String mntnNm, String pageNo, String numOfRows) throws UnsupportedEncodingException {
		RestTemplate restTemplate = new RestTemplate();

		// 서비스 키를 디코딩합니다.
		String serviceKey = URLDecoder.decode(encodedServiceKey, "UTF-8");

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://openapi.forest.go.kr/openapi/service/trailInfoService/getforestspatialdataservice")
			.queryParam("serviceKey", serviceKey)
			.queryParam("mntnNm", mntnNm)
			.queryParam("pageNo", pageNo)
			.queryParam("numOfRows", numOfRows);

		URI uri = builder.encode().build().toUri();

		return restTemplate.getForObject(uri, String.class);
	}
}

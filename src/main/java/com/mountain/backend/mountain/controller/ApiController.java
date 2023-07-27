// package com.mountain.backend.mountain.controller;
//
//
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;
//
// import java.io.UnsupportedEncodingException;
//
// import com.mountain.backend.mountain.service.MountainInfoService;
//
// @RestController
// @RequestMapping("/api")
// public class ApiController {
//
// 	private final MountainInfoService mountainInfoService;
//
// 	public ApiController(MountainInfoService mountainInfoService) {
// 		this.mountainInfoService = mountainInfoService;
// 	}
//
// 	@GetMapping("/forestData")
// 	public ResponseEntity<String> getForestData(@RequestParam String mntnNm, @RequestParam String pageNo, @RequestParam String numOfRows) {
// 		String response = null;
// 		try {
// 			response = mountainInfoService.getForestData(mntnNm, pageNo, numOfRows);
// 		} catch (UnsupportedEncodingException e) {
// 			e.printStackTrace();
// 			// 적절한 오류 응답을 반환하도록 코드를 수정하세요.
// 		}
//
// 		return ResponseEntity.ok(response);
// 	}
// }
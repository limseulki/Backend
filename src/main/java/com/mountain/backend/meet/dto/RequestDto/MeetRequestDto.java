package com.mountain.backend.meet.dto.RequestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MeetRequestDto {

    private String title;       // 모임명

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")    // hh -> HH
    private LocalDateTime departureDate;   // 모임일정

    private int meetSize;        // 모집인원

    private String age;         // 연령대

    private String gender;      // 성별

    private String tag;         // 태그

    private String location;    // 모임위치 - 산일수도 있고 식당일수도 있음

    private String course;       // 등산코스 - 산일경우

    private String content;     // 모임설명

    private MultipartFile imgUrl;      // 이미지

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate openDate;    // 모집시작일

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate closingDate;     // 모집마감일

}

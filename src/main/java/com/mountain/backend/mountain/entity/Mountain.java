package com.mountain.backend.mountain.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jdk.jfr.Name;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@NoArgsConstructor
@Getter
public class Mountain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Name("mountainId")
    private Long id;


    private String mountainName;        // 산이름
    private String mountainAddress;     // 산주소
    @Column(columnDefinition = "TEXT")
    private String mountainInfo;        // 산정보
    private Integer mountainHeight;     // 산높이



    @OneToMany(mappedBy = "mountain", cascade = CascadeType.ALL)
    private List<Trail> trails;


}



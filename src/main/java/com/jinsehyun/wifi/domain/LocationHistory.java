package com.jinsehyun.wifi.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "HISTORY")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class LocationHistory {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 경도
    @Column(name = "x", nullable = false)
    private Double x;

    // 위도
    @Column(name = "y", nullable = false)
    private Double y;

    @Column(name = "query_dttm")
    private String queryDttm;
}

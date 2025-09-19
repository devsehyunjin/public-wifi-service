package com.jinsehyun.wifi.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "WIFI_AP")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WifiAp {

    @Id
    @Column(name = "mgr_no")
    private String mgrNo;

    @Column(name = "wrdofc")
    private String wrdofc;     // 자치구/설치기관

    @Column(name = "main_nm")
    private String mainNm;     // 와이파이명

    @Column(name = "adres1")
    private String adres1;

    @Column(name = "adres2")
    private String adres2;

    @Column(name = "instl_floor")
    private String instlFloor;

    @Column(name = "instl_ty")
    private String instlTy;

    @Column(name = "instl_mby")
    private String instlMby;

    @Column(name = "svc_se")
    private String svcSe;

    @Column(name = "cmcwr")
    private String cmcwr;

    @Column(name = "cnstc_year")
    private String cnstcYear;

    @Column(name = "inout_door")
    private String inoutDoor;

    @Column(name = "remars3")
    private String remars3;

    private Double lat;
    private Double lnt;

    @Column(name = "work_dttm")
    private String workDttm; // 데이터 갱신일시
}

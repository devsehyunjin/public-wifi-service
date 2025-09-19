package com.jinsehyun.wifi.dto;

import com.google.gson.annotations.SerializedName;
import com.jinsehyun.wifi.domain.WifiAp;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class WifiApiEnvelope {
    @SerializedName("TbPublicWifiInfo")
    private TbPublicWifiInfo body;

    @Getter
    public static class TbPublicWifiInfo {
        @SerializedName("list_total_count")
        private int total;

        private List<Row> row;
    }

    @Getter
    public static class Row {
        @SerializedName("X_SWIFI_MGR_NO")    private String mgrNo;
        @SerializedName("X_SWIFI_WRDOFC")    private String wrdofc;
        @SerializedName("X_SWIFI_MAIN_NM")   private String mainNm;
        @SerializedName("X_SWIFI_ADRES1")    private String adres1;
        @SerializedName("X_SWIFI_ADRES2")    private String adres2;
        @SerializedName("X_SWIFI_INSTL_FLOOR") private String instlFloor;
        @SerializedName("X_SWIFI_INSTL_TY")    private String instlTy;
        @SerializedName("X_SWIFI_INSTL_MBY")   private String instlMby;
        @SerializedName("X_SWIFI_SVC_SE")      private String svcSe;
        @SerializedName("X_SWIFI_CMCWR")       private String cmcwr;
        @SerializedName("X_SWIFI_CNSTC_YEAR")  private String cnstcYear;
        @SerializedName("X_SWIFI_INOUT_DOOR")  private String inoutDoor;
        @SerializedName("X_SWIFI_REMARS3")     private String remars3;
        @SerializedName("LAT")                private Double lat;  // 위도
        @SerializedName("LNT")                private Double lnt;  // 경도
        @SerializedName("WORK_DTTM")          private String workDttm;

        public WifiAp toEntity() {
            return WifiAp.builder()
                    .mgrNo(mgrNo)
                    .wrdofc(wrdofc)
                    .mainNm(mainNm)
                    .adres1(adres1)
                    .adres2(adres2)
                    .instlFloor(instlFloor)
                    .instlTy(instlTy)
                    .instlMby(instlMby)
                    .svcSe(svcSe)
                    .cmcwr(cmcwr)
                    .cnstcYear(cnstcYear)
                    .inoutDoor(inoutDoor)
                    .remars3(remars3)
                    .lat(lat)
                    .lnt(lnt)
                    .workDttm(workDttm)
                    .build();
        }
    }

    public int totalCount() {
        return body != null ? body.getTotal() : 0;
    }
    public List<WifiAp> toEntities() {
        return body != null && body.getRow() != null
                ? body.getRow().stream().map(Row::toEntity).collect(Collectors.toList())
                : List.of();
    }
}
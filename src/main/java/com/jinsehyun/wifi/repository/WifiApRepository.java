package com.jinsehyun.wifi.repository;

import com.jinsehyun.wifi.dto.NearbyWifiView;
import com.jinsehyun.wifi.domain.WifiAp;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WifiApRepository extends JpaRepository<WifiAp, String> {

    // 기존 findNearby(...)는 있어도 되고, 아래 메서드를 쓰는 걸 권장합니다.
    @Query(value = """
    SELECT
      (6371 * acos(
        cos(radians(:lat)) * cos(radians(lat)) *
        cos(radians(lnt) - radians(:lnt)) +
        sin(radians(:lat)) * sin(radians(lat))
      )) AS distance_km,
      mgr_no AS mgrNo,
      wrdofc,
      main_nm AS mainNm,
      adres1,
      adres2,
      instl_floor AS instlFloor,
      instl_ty AS instlTy,
      instl_mby AS instlMby,
      svc_se AS svcSe,
      cmcwr,
      cnstc_year AS cnstcYear,
      inout_door AS inoutDoor,
      remars3,
      lat,
      lnt,
      work_dttm AS workDttm
    FROM WIFI_AP
    WHERE lat IS NOT NULL AND lnt IS NOT NULL
    ORDER BY distance_km ASC
    LIMIT 20
    """, nativeQuery = true)

    List<NearbyWifiView> findNearbyWithDistance(@Param("lat") double lat, @Param("lnt") double lnt);
}
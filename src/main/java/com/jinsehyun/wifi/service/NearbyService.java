package com.jinsehyun.wifi.service;

import com.jinsehyun.wifi.domain.LocationHistory;
import com.jinsehyun.wifi.dto.NearbyWifiView;
import com.jinsehyun.wifi.repository.LocationHistoryRepository;
import com.jinsehyun.wifi.repository.WifiApRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NearbyService {
    private final WifiApRepository wifiApRepository;
    private final LocationHistoryRepository historyRepository;

    @Transactional
    public List<NearbyWifiView> findAndRecord(double lat, double lnt) {
        // 1) 히스토리 저장
        historyRepository.save(
                LocationHistory.builder()
                        .x(lnt).y(lat)
                        .queryDttm(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                        .build()
        );
        // 2) 거리 포함 조회
        return wifiApRepository.findNearbyWithDistance(lat, lnt);
    }
}
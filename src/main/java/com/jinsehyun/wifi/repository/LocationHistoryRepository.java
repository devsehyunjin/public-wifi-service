package com.jinsehyun.wifi.repository;

import com.jinsehyun.wifi.domain.LocationHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocationHistoryRepository extends JpaRepository<LocationHistory, Long> {
    List<LocationHistory> findAllByOrderByIdDesc();
}

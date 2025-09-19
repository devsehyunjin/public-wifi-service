package com.jinsehyun.wifi.service;

import com.google.gson.Gson;
import com.jinsehyun.wifi.domain.WifiAp;
import com.jinsehyun.wifi.dto.WifiApiEnvelope;
import com.jinsehyun.wifi.repository.WifiApRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class WifiImportService {

    private final WifiApRepository wifiApRepository;
    private final OkHttpClient client = new OkHttpClient();
    private final Gson gson = new Gson();

    @Value("${seoul.api-key}")
    private String apiKey;

    private static final String HOST = "openapi.seoul.go.kr";
    private static final int PORT = 8088; // 공식 문서 기준
    private static final String PATH_DATASET = "TbPublicWifiInfo";
    private static final int PAGE_SIZE = 1000; // API가 허용하는 최대 범위(1~1000) 권장

    @Transactional
    public int importAll() throws IOException {
        int totalSaved = 0;

        // 1) totalCount 파악을 위해 1페이지 호출
        var first = fetch(1, PAGE_SIZE);
        int total = first.totalCount();
        var firstBatch = first.toEntities();
        wifiApRepository.saveAll(firstBatch);
        totalSaved += firstBatch.size();

        // 2) 나머지 페이지 루프
        for (int start = PAGE_SIZE + 1; start <= total; start += PAGE_SIZE) {
            int end = Math.min(start + PAGE_SIZE - 1, total);
            var env = fetch(start, end - start + 1); // fetch 내부에서 /start/end 형태로 처리
            List<WifiAp> batch = env.toEntities();
            if (!batch.isEmpty()) {
                wifiApRepository.saveAll(batch);
                totalSaved += batch.size();
            }
            log.info("Saved {} / {}", totalSaved, total);
        }

        log.info("Import done. totalSaved={}", totalSaved);
        return totalSaved;
    }

    private WifiApiEnvelope fetch(int start, int size) throws IOException {
        int end = start + size - 1;

        HttpUrl url = new HttpUrl.Builder()
                .scheme("http")
                .host(HOST)
                .port(PORT)
                .addPathSegment(apiKey)
                .addPathSegment("json")
                .addPathSegment(PATH_DATASET)
                .addPathSegment(String.valueOf(start))
                .addPathSegment(String.valueOf(end))
                .build();

        Request req = new Request.Builder().url(url).get().build();
        try (Response resp = client.newCall(req).execute()) {
            if (!resp.isSuccessful()) {
                throw new IOException("Seoul API error: " + resp.code());
            }
            String body = resp.body().string();
            return gson.fromJson(body, WifiApiEnvelope.class);
        }
    }
}
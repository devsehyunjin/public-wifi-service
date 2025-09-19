package com.jinsehyun.wifi.controller;

import com.jinsehyun.wifi.dto.NearbyWifiView;
import com.jinsehyun.wifi.service.NearbyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class NearbyController {

    private final NearbyService nearbyService;

    @GetMapping("/nearby")
    public String nearby(@RequestParam("lat") double lat,
                         @RequestParam("lnt") double lnt,
                         Model model) {

        List<NearbyWifiView> items = nearbyService.findAndRecord(lat, lnt);
        model.addAttribute("lat", lat);
        model.addAttribute("lnt", lnt);
        model.addAttribute("items", items);
        return "nearby"; // /WEB-INF/views/nearby.jsp
    }
}
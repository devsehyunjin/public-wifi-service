package com.jinsehyun.wifi.controller;

import com.jinsehyun.wifi.service.WifiImportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ImportController {

    private final WifiImportService wifiImportService;

    @GetMapping("/import/wifi")
    public String importWifi(Model model) throws Exception {
        int saved = wifiImportService.importAll();
        model.addAttribute("saved", saved);
        return "import-result";
    }
}
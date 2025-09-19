package com.jinsehyun.wifi.controller;

import com.jinsehyun.wifi.repository.LocationHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class HistoryController {

    private final LocationHistoryRepository historyRepository;

    // 목록
    @GetMapping("/history")
    public String list(Model model) {
        model.addAttribute("items", historyRepository.findAllByOrderByIdDesc());
        return "history";
    }

    // 삭제
    @GetMapping("/history/delete")
    public String delete(@RequestParam("id") Long id) {
        historyRepository.deleteById(id);
        return "redirect:/history";
    }
}

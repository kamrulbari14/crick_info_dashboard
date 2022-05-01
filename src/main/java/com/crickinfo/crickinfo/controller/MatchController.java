package com.crickinfo.crickinfo.controller;

import com.crickinfo.crickinfo.service.MatchService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MatchController {

    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping("/dashboard")
    public String getAllScores(Model model) {
        model.addAttribute("scoreList", matchService.getAllScores());
        return "dashboard";
    }
}

package com.crickinfo.crickinfo.controller;

import com.crickinfo.crickinfo.service.DashboardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/dashboard")
    public String getAllScores(Model model) {
        model.addAttribute("scoreList", dashboardService.getAllScores());
        return "dashboard";
    }

    @GetMapping("/")
    public String dashboard() {
        return "redirect:/dashboard";
    }
}

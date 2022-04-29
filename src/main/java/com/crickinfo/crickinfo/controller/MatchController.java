package com.crickinfo.crickinfo.controller;

import com.crickinfo.crickinfo.service.MatchService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MatchController {

    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping("/xml")
    public void getXmlData(){
        System.out.println(matchService.getDataFromXml());
    }
}

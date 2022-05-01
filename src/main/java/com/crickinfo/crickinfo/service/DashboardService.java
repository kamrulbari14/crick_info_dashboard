package com.crickinfo.crickinfo.service;

import com.crickinfo.crickinfo.dto.ScoreDto;
import com.crickinfo.crickinfo.entity.Score;

import java.util.List;

public interface DashboardService {
    void saveDataFromXml(Score score);

    List<ScoreDto> getAllScores();
}
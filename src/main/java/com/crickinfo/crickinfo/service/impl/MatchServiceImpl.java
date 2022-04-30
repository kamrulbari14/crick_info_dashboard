package com.crickinfo.crickinfo.service.impl;

import com.crickinfo.crickinfo.entity.Score;
import com.crickinfo.crickinfo.repository.ScoresRepository;
import com.crickinfo.crickinfo.service.MatchService;
import org.springframework.stereotype.Service;

@Service
public class MatchServiceImpl implements MatchService {

    private final ScoresRepository scoresRepository;

    public MatchServiceImpl(ScoresRepository scoresRepository) {
        this.scoresRepository = scoresRepository;
    }

    @Override
    public void saveDataFromXml(Score score) {
        scoresRepository.save(score);
    }
}

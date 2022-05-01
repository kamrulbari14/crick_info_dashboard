package com.crickinfo.crickinfo.service.impl;

import com.crickinfo.crickinfo.dto.ScoreDto;
import com.crickinfo.crickinfo.entity.Score;
import com.crickinfo.crickinfo.enums.ActiveStatus;
import com.crickinfo.crickinfo.repository.ScoresRepository;
import com.crickinfo.crickinfo.service.MatchService;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MatchServiceImpl implements MatchService {

    private final ScoresRepository scoresRepository;

    private final ModelMapper modelMapper;

    public MatchServiceImpl(ScoresRepository scoresRepository, ModelMapper modelMapper) {
        this.scoresRepository = scoresRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void saveDataFromXml(Score score) {
        scoresRepository.save(score);
    }

    @Override
    public List<ScoreDto> getAllScores() {
        List<Score> scoreList = scoresRepository.findAllByActiveStatusOrderByIdDesc(ActiveStatus.ACTIVE.getValue());
        List<ScoreDto> scoreDtoList = new ArrayList<>();
        scoreList.forEach(score -> {
            modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
            scoreDtoList.add(modelMapper.map(score, ScoreDto.class));
        });
        return scoreDtoList;
    }
}

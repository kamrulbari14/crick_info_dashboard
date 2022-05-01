package com.crickinfo.crickinfo.scheduler;

import com.crickinfo.crickinfo.dto.Rss;
import com.crickinfo.crickinfo.entity.Score;
import com.crickinfo.crickinfo.repository.ScoresRepository;
import com.crickinfo.crickinfo.service.DashboardService;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.net.URL;
import java.util.List;
import java.util.Optional;

@Service
public class ScoresScheduler {

    private final DashboardService dashboardService;
    private final ScoresRepository scoresRepository;
    private final ModelMapper modelMapper;

    public ScoresScheduler(DashboardService dashboardService, ScoresRepository scoresRepository, ModelMapper modelMapper) {
        this.dashboardService = dashboardService;
        this.scoresRepository = scoresRepository;
        this.modelMapper = modelMapper;
    }

    @Scheduled(cron = "0 0/5 * * * *")
    public void parseDataFromXml() {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new URL("http://static.cricinfo.com/rss/livescores.xml").openStream());
            JAXBContext jaxbContext = JAXBContext.newInstance(Rss.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Rss matchHistory = (Rss) unmarshaller.unmarshal(doc);
            List<Score> scoreList = scoresRepository.findAll();
            matchHistory.getChannel().getItem().forEach(scoreDto -> {
                Score scoreEntity;
                Optional<Score> existence = scoreList.stream().filter(score -> score.getLink().equals(scoreDto.getLink())).findFirst();
                if (existence.isPresent()) {
                    scoreEntity = existence.get();
                    modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
                    modelMapper.map(scoreDto, scoreEntity);
                    dashboardService.saveDataFromXml(scoreEntity);
                } else {
                    scoreEntity = modelMapper.map(scoreDto, Score.class);
                    dashboardService.saveDataFromXml(scoreEntity);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
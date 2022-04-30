package com.crickinfo.crickinfo.scheduler;

import com.crickinfo.crickinfo.dto.Rss;
import com.crickinfo.crickinfo.entity.Score;
import com.crickinfo.crickinfo.repository.ScoresRepository;
import com.crickinfo.crickinfo.service.MatchService;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScoresScheduler {

    private final MatchService matchService;
    private final ScoresRepository scoresRepository;
    private final ModelMapper modelMapper;

    public ScoresScheduler(MatchService matchService, ScoresRepository scoresRepository, ModelMapper modelMapper) {
        this.matchService = matchService;
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
            Rss matchDto = (Rss) unmarshaller.unmarshal(doc);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            System.out.println(dtf.format(now));
            System.out.println(matchDto);
            List<Score> scores = scoresRepository.findAll();
            matchDto.getChannel().getItem().forEach(score -> {
                if(! scores.stream().map(Score::getTitle).collect(Collectors.toList()).contains(score.getTitle())){
                    Score score1 = modelMapper.map(score, Score.class);
                    matchService.saveDataFromXml(score1);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

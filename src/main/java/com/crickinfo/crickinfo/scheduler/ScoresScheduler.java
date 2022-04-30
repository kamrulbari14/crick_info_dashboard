package com.crickinfo.crickinfo.scheduler;

import com.crickinfo.crickinfo.dto.Item;
import com.crickinfo.crickinfo.dto.Rss;
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

@Service
public class ScoresScheduler {

    @Scheduled(cron = "0 0/5 * * * *")
    public List<Item> parseDataFromXml() {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

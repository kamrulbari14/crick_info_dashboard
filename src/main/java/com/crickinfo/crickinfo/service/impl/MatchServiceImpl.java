package com.crickinfo.crickinfo.service.impl;

import com.crickinfo.crickinfo.dto.Channel;
import com.crickinfo.crickinfo.dto.Rss;
import com.crickinfo.crickinfo.service.MatchService;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.net.URL;
import java.util.List;

@Service
public class MatchServiceImpl implements MatchService {
    @Override
    public List<Channel> getDataFromXml() {

        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new URL("http://static.cricinfo.com/rss/livescores.xml").openStream());
            JAXBContext jaxbContext = JAXBContext.newInstance(Rss.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Rss matchDto = (Rss) unmarshaller.unmarshal(doc);
            System.out.println(matchDto);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }
}

package com.crickinfo.crickinfo.dto;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement
public class Channel {
    private String title;
    private String ttl;
    private String link;
    private String description;
    private String copyright;
    private String language;
    private String pubDate;
    private Item item;

}

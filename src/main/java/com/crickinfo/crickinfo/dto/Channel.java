package com.crickinfo.crickinfo.dto;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Data
@XmlRootElement
public class Channel extends BaseDto {
    private String title;
    private String ttl;
    private String link;
    private String description;
    private String copyright;
    private String language;
    private String pubDate;
    private List<Item> item;

}

package com.crickinfo.crickinfo.dto;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement
public class Item {
    private String title;
    private String link;
    private String description;
    private String guid;
}

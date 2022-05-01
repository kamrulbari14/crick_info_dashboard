package com.crickinfo.crickinfo.dto;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement(name = "item")
public class ScoreDto extends BaseDto {
    private String title;
    private String link;
    private String description;
    private String guid;
}
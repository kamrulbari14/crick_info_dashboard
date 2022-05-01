package com.crickinfo.crickinfo.dto;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement
public class Rss {
    private Channel channel;
}
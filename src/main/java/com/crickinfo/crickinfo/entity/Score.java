package com.crickinfo.crickinfo.entity;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class Score extends BaseEntity {
    private String title;
    private String link;
    private String description;
    private String guid;
}
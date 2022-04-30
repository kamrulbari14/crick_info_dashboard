package com.crickinfo.crickinfo.service;

import com.crickinfo.crickinfo.dto.Item;

import java.util.List;

public interface MatchService {
    void saveDataFromXml(List<Item> items);
}

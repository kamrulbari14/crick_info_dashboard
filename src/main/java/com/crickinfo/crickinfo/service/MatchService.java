package com.crickinfo.crickinfo.service;

import com.crickinfo.crickinfo.dto.Channel;

import java.util.List;

public interface MatchService {
    List<Channel> getDataFromXml();
}

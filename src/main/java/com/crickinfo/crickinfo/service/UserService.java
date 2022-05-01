package com.crickinfo.crickinfo.service;

import com.crickinfo.crickinfo.dto.UserDto;
import com.crickinfo.crickinfo.entity.User;

public interface UserService {
    User save(UserDto userDto);
}
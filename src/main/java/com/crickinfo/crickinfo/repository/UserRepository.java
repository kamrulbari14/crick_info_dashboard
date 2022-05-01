package com.crickinfo.crickinfo.repository;

import com.crickinfo.crickinfo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserNameAndActiveStatus(String userName, Integer activeStatus);
}
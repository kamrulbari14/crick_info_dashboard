package com.crickinfo.crickinfo.repository;

import com.crickinfo.crickinfo.entity.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoresRepository extends JpaRepository<Score, Long> {

    List<Score> findAllByActiveStatusOrderByIdDesc(Integer activeStatus);
}

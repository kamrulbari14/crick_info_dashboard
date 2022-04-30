package com.crickinfo.crickinfo.repository;

import com.crickinfo.crickinfo.entity.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoresRepository extends JpaRepository<Score, Long> {
}

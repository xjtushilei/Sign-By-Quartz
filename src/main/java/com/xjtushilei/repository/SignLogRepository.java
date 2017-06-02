package com.xjtushilei.repository;

import com.xjtushilei.domain.AutoSignLog;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SignLogRepository extends JpaRepository<AutoSignLog, Long> {

}
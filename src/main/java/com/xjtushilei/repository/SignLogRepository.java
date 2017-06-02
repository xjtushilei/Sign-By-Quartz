package com.xjtushilei.repository;

import com.xjtushilei.domain.AutoSignLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;


public interface SignLogRepository extends JpaRepository<AutoSignLog, Long> {

    List<AutoSignLog> findByEmailAndLocalDateTimeAfter(String email, Date date);

}
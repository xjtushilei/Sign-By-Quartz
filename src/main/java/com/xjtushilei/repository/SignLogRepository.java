package com.xjtushilei.repository;

import com.xjtushilei.domain.AutoSignLog;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;


public interface SignLogRepository extends JpaRepository<AutoSignLog, Long> {

    List<AutoSignLog> findByEmailAndLocalDateTimeAfter(String email, Date date);

    @Query("select log from AutoSignLog log where log.email= :email order by log.localDateTime desc ")
    List<AutoSignLog> findByEmail(Pageable pagable, @Param("email") String email);

    @Query("select log from AutoSignLog log order by log.localDateTime desc ")
    List<AutoSignLog> findALlLog(Pageable pagable);
}
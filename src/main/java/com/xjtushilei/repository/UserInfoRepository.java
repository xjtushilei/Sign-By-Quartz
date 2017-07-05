package com.xjtushilei.repository;

import com.xjtushilei.domain.AutoSignUserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserInfoRepository extends JpaRepository<AutoSignUserInfo, Long> {

    List<AutoSignUserInfo> findByAutoSignIsTrue();

    List<AutoSignUserInfo> findBySendEmailIsTrue();
}
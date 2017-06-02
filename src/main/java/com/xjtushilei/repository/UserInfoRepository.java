package com.xjtushilei.repository;

import com.xjtushilei.domain.AutoSignUserInfo;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserInfoRepository extends JpaRepository<AutoSignUserInfo, Long> {

}
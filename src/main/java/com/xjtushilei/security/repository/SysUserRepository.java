package com.xjtushilei.security.repository;

import com.xjtushilei.security.domain.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SysUserRepository extends JpaRepository<SysUser, Long> {
    SysUser findByUsername(String username);

    SysUser findByUsernameAndPassword(String name, String password);
}

package cn.szsyph.dcd.repository.dao;

import cn.szsyph.dcd.repository.domain.UserClick;
import cn.szsyph.dcd.repository.domain.UserPin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserClickDao extends JpaRepository<UserClick, Long> {

    List<UserClick> findByUserId(long userId);
}

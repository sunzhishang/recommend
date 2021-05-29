package cn.szsyph.dcd.repository.dao;

import cn.szsyph.dcd.repository.domain.UserGrade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserGradeDao extends JpaRepository<UserGrade, Long> {

    List<UserGrade> findByUserId(long userId);

    UserGrade findByUserIdAndArticleId(long userId, long articleId);
}

package cn.szsyph.dcd.repository.dao;


import cn.szsyph.dcd.repository.domain.UserPin;

import java.util.List;

/**
 * @Description 用户行为
 * @Author sunzhishang
 * @Version V1.0
 */

public interface UserPinDao extends org.springframework.data.jpa.repository.JpaRepository<UserPin, Long> {

    List<UserPin> findByUserId(long userId);

    void deleteByUserIdAndArticleId(long userId, long articleId);

    UserPin findByUserIdAndArticleId(long userId, long articleId);
}

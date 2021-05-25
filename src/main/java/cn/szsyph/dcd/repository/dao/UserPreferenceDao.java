package cn.szsyph.dcd.repository.dao;


import cn.szsyph.dcd.repository.domain.UserPreference;

/**
 * @Description 用户偏好
 * @Author sunzhishang
 * @Version V1.0
 */
public interface UserPreferenceDao extends org.springframework.data.jpa.repository.JpaRepository<UserPreference, Long> {
    UserPreference findByUserIdAndArticleId(long userId, long articleId);
}

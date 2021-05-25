package cn.szsyph.dcd.service;

import cn.szsyph.dcd.repository.dao.UserPreferenceDao;
import cn.szsyph.dcd.repository.domain.User;
import cn.szsyph.dcd.repository.domain.UserPreference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserPreferenceService {

    @Autowired
    private UserPreferenceDao userPreferenceDao;

    public void saveOrUpdateUserPreference(long userId, long articleId, double preference) {
        UserPreference byUserIdAndArticleId = userPreferenceDao.findByUserIdAndArticleId(userId, articleId);
        if (byUserIdAndArticleId != null && byUserIdAndArticleId.getUserId() != 0 && byUserIdAndArticleId.getArticleId() != 0) {
            byUserIdAndArticleId.setPreference(byUserIdAndArticleId.getPreference()+preference);
            userPreferenceDao.save(byUserIdAndArticleId);
            return;
        }
        UserPreference newOne =new UserPreference(userId, articleId, preference);
        userPreferenceDao.save(newOne);
    }

}


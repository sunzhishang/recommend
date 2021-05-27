package cn.szsyph.dcd.service;

import cn.szsyph.dcd.repository.dao.UserPinDao;
import cn.szsyph.dcd.repository.domain.UserPin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserPinService {
    @Autowired
    private UserPinDao userPinDao;

    public UserPin getUserPinByUserIdAndArticleId(long userId, long articleId) {
        return userPinDao.findByUserIdAndArticleId(userId, articleId);
    }
}

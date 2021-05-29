package cn.szsyph.dcd.service;

import cn.szsyph.dcd.repository.dao.UserDao;
import cn.szsyph.dcd.repository.dao.UserGradeDao;
import cn.szsyph.dcd.repository.domain.UserGrade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserGradeService {
    @Autowired
    private UserGradeDao userGradeDao;

    public UserGrade getUserGradeByUserIdAndArticleId(long userId, long articleId) {
        return userGradeDao.findByUserIdAndArticleId(userId, articleId);
    }
}

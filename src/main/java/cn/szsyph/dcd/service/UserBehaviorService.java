package cn.szsyph.dcd.service;


import cn.szsyph.dcd.enums.UserBehaviorEnum;
import cn.szsyph.dcd.repository.dao.UserClickDao;
import cn.szsyph.dcd.repository.dao.UserGradeDao;
import cn.szsyph.dcd.repository.dao.UserPinDao;
import cn.szsyph.dcd.repository.domain.ArticleES;
import cn.szsyph.dcd.repository.domain.UserClick;
import cn.szsyph.dcd.repository.domain.UserGrade;
import cn.szsyph.dcd.repository.domain.UserPin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserBehaviorService {

    @Autowired
    private UserPreferenceService userPreferenceService;

    @Autowired
    private UserPinDao userPinDao;

    @Autowired
    private UserClickDao userClickDao;

    @Autowired
    private UserGradeDao userGradeDao;

    @Autowired
    private RecommendRankingArticleService recommendRankingArticleService;

    public void clickArticle(long userId, long articleId) {
        recommendRankingArticleService.pushRanking(articleId, UserBehaviorEnum.CLICK.getFactor());
        if (userId != 0) {
            userClickDao.save(new UserClick(userId, articleId, null));
            userPreferenceService.saveOrUpdateUserPreference(userId, articleId, UserBehaviorEnum.CLICK.getFactor());
        }
    }

    public void pinArticle(long userId, long articleId) {
        recommendRankingArticleService.pushRanking(articleId, UserBehaviorEnum.CLICK.getFactor());
        if (userId != 0) {
            userPinDao.save(new UserPin(userId, articleId, null));
            userPreferenceService.saveOrUpdateUserPreference(userId, articleId, UserBehaviorEnum.PIN.getFactor());
        }
    }

    public void gradeArticle(long userId, long articleId, int grade) {
        UserBehaviorEnum behaviorEnumByGrade = UserBehaviorEnum.getBehaviorEnumByGrade(grade);
        if (behaviorEnumByGrade == null) {
            return;
        }
        recommendRankingArticleService.pushRanking(articleId, behaviorEnumByGrade.getFactor());
        if (userId != 0) {
            userGradeDao.save(new UserGrade(userId, articleId, grade, null));
            userPreferenceService.saveOrUpdateUserPreference(userId, articleId, behaviorEnumByGrade.getFactor());
        }
    }

    public void searchArticles(long userId, List<ArticleES> articleIdList, int pageNo, int pageSize) {
        int searchOffset = pageNo * pageSize;
        for (int i = 0; i < articleIdList.size(); i++) {
            int searchRanking = searchOffset + i + 1;
            double score = 5 / (double) searchRanking;
            recommendRankingArticleService.pushRanking(articleIdList.get(i).getId(),score);
            if (userId != 0) {
                userPreferenceService.saveOrUpdateUserPreference(userId, articleIdList.get(i).getId(), score);
            }
        }
    }




}

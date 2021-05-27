package cn.szsyph.dcd.service.recommender;

import cn.szsyph.dcd.repository.dao.UserPinDao;
import cn.szsyph.dcd.repository.domain.Article;
import cn.szsyph.dcd.repository.domain.ArticleApi;
import cn.szsyph.dcd.repository.domain.UserPin;
import cn.szsyph.dcd.service.ArticleService;
import cn.szsyph.dcd.service.RecommendRankingArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class HotRecommenderService {

    @Autowired
    private RecommendRankingArticleService recommendRankingArticleService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private UserPinDao userPinDao;

    public List<Article> recommend(long userId, int pageNo, int pageSize) {
        if (pageSize == 0) {
            return null;
        }
        List<Long> articleIds = recommendRankingArticleService.getArticles(pageNo, pageSize);
        List<Article> result = new ArrayList<>();
        for (Long id : articleIds) {
            Article articleById = articleService.getArticleById(id);
            if (userId != 0) {
                UserPin userPin = userPinDao.findByUserIdAndArticleId(userId, id);
                if (userPin.getArticleId() != 0 && userPin.getUserId() != 0) {
                    ArticleApi articleApi = new ArticleApi(articleById);
                    articleApi.setPined(true);
                    result.add(articleApi);
                    continue;
                }
            }
            result.add(articleById);
        }
        return result;
    }

}

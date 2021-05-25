package cn.szsyph.dcd.service.recommender;

import cn.szsyph.dcd.repository.domain.Article;
import cn.szsyph.dcd.service.ArticleService;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static cn.szsyph.dcd.constant.Constant.DEFAULT_GET_RECOMMEND_NUM;

@Service
public class ItemBasedSchedulerService {

    @Autowired
    private ItemBasedRecommenderService itemBasedRecommenderService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private ArticleService articleService;

    private String prefix = "item_based_";

    public List<Article> recommend(long userId, int num) throws TasteException {
        if (userId == 0 || num <= 0) {
            return null;
        }
        List<String> articleIds = stringRedisTemplate.boundListOps(prefix + userId).range(0, num);
        if (articleIds == null || articleIds.size() == 0) {
            refresh(userId);
            return null;
        }
        List<Article> articles = new ArrayList<>();
        for (String articleId : articleIds) {
            articles.add(articleService.getArticleById(Long.parseLong(articleId)));
        }
        stringRedisTemplate.boundListOps(prefix + userId).trim(num, -1);
        refresh(userId);
        return articles;
    }

    @Async
    public void refresh(long userId) throws TasteException {
        List<String> articleQueue = stringRedisTemplate.boundListOps(prefix + userId).range(0, -1);
        if (articleQueue != null && articleQueue.size() > 50) {
            return;
        }
        itemBasedRecommenderService.refresh(userId);
    }
}

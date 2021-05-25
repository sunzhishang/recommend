package cn.szsyph.dcd.service.recommender;

import cn.szsyph.dcd.repository.domain.Article;
import cn.szsyph.dcd.service.ArticleService;
import cn.szsyph.dcd.service.RecommendRankingArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static cn.szsyph.dcd.constant.Constant.DEFAULT_RECOMMEND_NUM;


@Service
public class HotRecommenderService implements recommenderService {

    @Autowired
    private RecommendRankingArticleService recommendRankingArticleService;

    @Autowired
    private ArticleService articleService;

    @Override
    public List<Article> recommend(long userId, int num) {
        if (num == 0) {
            return null;
        }
        List<Long> articleIds = recommendRankingArticleService.getTopN(DEFAULT_RECOMMEND_NUM);
        List<Article> result = new ArrayList<>();
        for (Long id : articleIds) {
            result.add(articleService.getArticleById(id));
        }
        return result;
    }

}

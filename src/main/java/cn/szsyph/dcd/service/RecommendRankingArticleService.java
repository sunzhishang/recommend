package cn.szsyph.dcd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class RecommendRankingArticleService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    private final String ArticleRankingKey = "article_ranking";

    public void pushRanking(long articleId, double score) {
        stringRedisTemplate.boundZSetOps(ArticleRankingKey).incrementScore(Long.toString(articleId), score);
    }

    public List<Long> getArticles(int pageNo, int pageSize) {
        if (pageSize < 1) {
            return null;
        }
        Set<String> articleIdStrTopN = stringRedisTemplate.boundZSetOps(ArticleRankingKey).reverseRange(pageNo * pageSize, (pageNo + 1) * pageSize);
        if (articleIdStrTopN == null) {
            return null;
        }
        return Set2List(articleIdStrTopN);
    }

    public List<Long> Set2List(Set<String> topN) {
        List<Long> result = new ArrayList<>();
        for (String s : topN) {
            result.add(Long.parseLong(s));
        }
        return result;
    }
}

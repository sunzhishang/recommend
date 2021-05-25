package cn.szsyph.dcd;

import cn.szsyph.dcd.repository.dao.ArticleDao;
import cn.szsyph.dcd.repository.dao.ArticleESDao;
import cn.szsyph.dcd.repository.dao.UserPreferenceDao;
import cn.szsyph.dcd.repository.domain.Article;
import cn.szsyph.dcd.repository.domain.ArticleES;
import cn.szsyph.dcd.repository.domain.UserPreference;
import cn.szsyph.dcd.service.RecommendRankingArticleService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.List;
import java.util.Random;

public class redisTest extends DcdApplicationTest {

    @Autowired
    private RecommendRankingArticleService recommendRankingArticleService;


    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private ArticleESDao articleESDao;

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private UserPreferenceDao userPreferenceDao;

    @Test
    public void TestRedisSet() {
        recommendRankingArticleService.pushRanking(1,100);
    }

    @Test
    public void TestRedisGet(){
        recommendRankingArticleService.getTopN(1);
    }

    @Test
    public void TestFather(){
        child1 a = new child1();
        System.out.println(a.test);
        a.test();
    }



    @Test
    public void Mysql2Redis(){
        List<Article> all = articleDao.findAll();
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            int userId = random.nextInt(9) + 1;
            int articleIdx = random.nextInt(all.size());
            double preference = random.nextDouble() * 10;
            UserPreference userPreference = new UserPreference(userId, all.get(articleIdx).getId(),preference);
            userPreferenceDao.save(userPreference);
        }
    }


}

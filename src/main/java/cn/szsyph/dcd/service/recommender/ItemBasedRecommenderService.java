package cn.szsyph.dcd.service.recommender;


import cn.szsyph.dcd.constant.Constant;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

import static cn.szsyph.dcd.constant.Constant.DEFAULT_GET_RECOMMEND_NUM;


@Service
public class ItemBasedRecommenderService {

    @Autowired
    private Recommender itemBasedRecommender;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private String prefix = "item_based_";

    @Async
    public void refresh(long userId) throws TasteException {
        List<RecommendedItem> recommend = itemBasedRecommender.recommend(userId, DEFAULT_GET_RECOMMEND_NUM);
        for (RecommendedItem item : recommend) {
            stringRedisTemplate.boundListOps(prefix + userId).rightPush(Long.toString(item.getItemID()));
        }
    }


}

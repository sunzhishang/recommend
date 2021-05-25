package cn.szsyph.dcd.service.recommender;

import cn.szsyph.dcd.repository.domain.Article;
import org.apache.mahout.cf.taste.common.TasteException;

import java.util.List;

public interface recommenderService {
    List<Article> recommend(long userId, int num) throws TasteException;
}

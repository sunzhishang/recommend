package cn.szsyph.dcd.config;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.CachingRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RecommenderConfig {

    @Autowired
    private DataModel dataModel;

    @Bean(name = "userBasedRecommender")
    public Recommender buildUserBasedRecommender() throws TasteException {
        PearsonCorrelationSimilarity pearsonCorrelationSimilarity = new PearsonCorrelationSimilarity(dataModel);
        NearestNUserNeighborhood nearestNUserNeighborhood = new NearestNUserNeighborhood(2, pearsonCorrelationSimilarity, dataModel);
        return new CachingRecommender(new GenericUserBasedRecommender(dataModel, nearestNUserNeighborhood, pearsonCorrelationSimilarity));
    }

    @Bean(name = "itemBasedRecommender")
    public Recommender buildItemBasedRecommender() throws TasteException {
        ItemSimilarity similarity = new PearsonCorrelationSimilarity(dataModel);
        return new CachingRecommender(new GenericItemBasedRecommender(dataModel, similarity));
    }
}

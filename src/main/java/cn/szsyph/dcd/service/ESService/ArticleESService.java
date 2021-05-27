package cn.szsyph.dcd.service.ESService;


import cn.szsyph.dcd.repository.domain.Article;
import cn.szsyph.dcd.repository.domain.ArticleES;
import cn.szsyph.dcd.repository.dao.ArticleESDao;
import cn.szsyph.dcd.repository.domain.ArticleApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class ArticleESService {
    @Autowired
    private ArticleESDao articleESDao;
    @Autowired
    private JsonProcessor jsonProcessor;
    @Autowired
    private DcdPipeline pipeline;

    private final DcdSpider spider = new DcdSpider();

    public String addArticle(int count) {
        ExecutorService executorService = Executors.newFixedThreadPool(30);
        for (int i = 0; i < count; i++) {
            executorService.execute(spider);
        }
        System.out.println("success");
        return "success";
    }

    public List<ArticleES> search(String keywords, int pageNo, int pageSize) {
        List<ArticleES> articles = new ArrayList<>();
        List<SearchHit<ArticleES>> hits = articleESDao.findByTitle(keywords, PageRequest.of(pageNo, pageSize));
        for (SearchHit<ArticleES> hit : hits) {
            ArticleES content = hit.getContent();
            content.setTitle(hit.getHighlightField("title").get(0));
            articles.add(content);
        }
        return articles;
    }

    public String deleteAll() {
        articleESDao.deleteAll();
        return "success";
    }

    class DcdSpider implements Runnable {
        String url = "https://www.dongchedi.com/motor/stream_entrance/get_feed/v47/?tt_from=enter_auto&category=motor_car&count=8&motor_feed_extra_params=%7B%22new_feed%22%3A%20true%2C%20%22feed_type%22%3A%200%7D&impression_info=%7B%22page_id%22%3A%22page_car_series%22%2C%22sub_tab%22%3A%22motor_car%22%2C%22product_name%22%3A%22web%22%7D&aid=1839&refer=1&channel=web&device_platform=web&web_id=261605083991208&motor_feed_extra_params=%7B%22new_feed%22%3A%20true%2C%20%22feed_type%22%3A%200%7D&source=pc";

        public void run() {
            Spider.create(jsonProcessor).addUrl(url)
                    .addPipeline(pipeline).thread(1).run();
        }
    }
}


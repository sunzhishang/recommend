package cn.szsyph.dcd.service.ESService;

import cn.szsyph.dcd.repository.dao.ArticleESDao;
import cn.szsyph.dcd.repository.domain.ArticleES;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;

@Component
public class DcdPipeline implements Pipeline {

    @Autowired
    public ArticleESDao articleESDao;

    public void process(ResultItems resultItems, Task task) {
        System.out.println("get page: " + resultItems.getRequest().getUrl());
        List<ArticleES> articles = resultItems.get("articles");
        for (ArticleES article : articles) {
            articleESDao.save(article);
        }
    }
}


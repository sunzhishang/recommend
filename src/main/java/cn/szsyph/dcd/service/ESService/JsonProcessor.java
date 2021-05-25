package cn.szsyph.dcd.service.ESService;

import cn.szsyph.dcd.repository.domain.ArticleES;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.JsonPathSelector;

import java.util.ArrayList;
import java.util.List;

@Component
public class JsonProcessor implements PageProcessor {
    private Site site = Site.me().setDomain("127.0.0.1");

    //处理数据的方法
    public void process(Page page) {
        List<ArticleES> articles = new ArrayList<>();
        // 由于是json格式的数据，这里采用JsonPathSelector专门处理
        // 具体语法可以参照：https://blog.csdn.net/weixin_37794119/article/details/81484885
        List<String> datalist = new JsonPathSelector("$.data").selectList(page.getRawText());
        // 这里拿到相关的数据 先放到page对象里面
        if (datalist != null && datalist.size() > 0) {
            for (int i = 0; i < datalist.size(); i++) {
                String data = datalist.get(i);
                JSONObject dataJson = JSONObject.parseObject(data);
                String articleId = dataJson.getString("unique_id_str");
                String title = dataJson.getJSONObject("info").getString("title");
                String videoId = dataJson.getJSONObject("info").getString("video_id");
                String imageurl = dataJson.getJSONObject("info").getJSONArray("image_list").getJSONObject(0).getString("url");
                String content;
                // 文章
                if (videoId == null || videoId.equals("")) {
                    content = ContentProcessor.GetContent(articleId);
                }
                // 视频
                else {
                    content = "";
                }
                ArticleES a = new ArticleES(Long.parseLong(articleId), title, imageurl, content);
                articles.add(a);
            }
        }
        page.putField("articles", articles);
    }

    public Site getSite() {
        return site;
    }
}

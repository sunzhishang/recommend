package cn.szsyph.dcd.repository.dao;


import cn.szsyph.dcd.repository.domain.ArticleES;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Highlight;
import org.springframework.data.elasticsearch.annotations.HighlightField;
import org.springframework.data.elasticsearch.annotations.HighlightParameters;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ArticleESDao extends ElasticsearchRepository<ArticleES, Long> {

    @Highlight(fields = {@HighlightField(name = "title")},
            parameters = @HighlightParameters(preTags = {"<mark style='color: rgb(250, 85, 85'>"}, postTags = {"</mark>"})
    )
    List<SearchHit<ArticleES>> findByTitle(String text);

    List<ArticleES> findByTitleOrContent(String title, String content);
}

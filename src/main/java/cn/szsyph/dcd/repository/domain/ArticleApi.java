package cn.szsyph.dcd.repository.domain;


import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ArticleApi extends Article {

    private static final long serialVersionUID = 5620257392935322012L;

    public ArticleApi(Article article) {
        super(article.getId(), article.getTitle(), article.getImage(), article.getContent());
        idStr = Long.toString(article.getId());
    }

    public ArticleApi(ArticleES articleES) {
        super(articleES.getId(), articleES.getTitle(), articleES.getImage(), articleES.getContent());
        idStr = Long.toString(articleES.getId());
    }

    private boolean isPined;

    private String idStr;
}

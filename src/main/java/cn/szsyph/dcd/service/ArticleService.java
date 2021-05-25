package cn.szsyph.dcd.service;

import cn.szsyph.dcd.repository.dao.ArticleDao;
import cn.szsyph.dcd.repository.domain.Article;
import cn.szsyph.dcd.repository.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ArticleService {


    @Autowired
    private ArticleDao articleDao;

    public void addArticle(int count) {

    }

    public void pinArticle(Long articleId) {

    }


    public void ratingArticle(Long articleId, int grade) {

    }

    public Article getArticleById(long articleId) {
        Optional<Article> byId = articleDao.findById(articleId);
        return byId.orElseGet(Article::new);
    }
}

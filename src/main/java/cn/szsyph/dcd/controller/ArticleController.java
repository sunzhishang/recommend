package cn.szsyph.dcd.controller;


import cn.szsyph.dcd.common.ResultUtil;
import cn.szsyph.dcd.common.ResultVo;
import cn.szsyph.dcd.repository.domain.ArticleES;
import cn.szsyph.dcd.service.ArticleService;
import cn.szsyph.dcd.service.ESService.ArticleESService;
import cn.szsyph.dcd.service.UserBehaviorService;
import cn.szsyph.dcd.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/motor/article")
public class ArticleController {

    @Autowired
    private ArticleESService articleESService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private UserBehaviorService userBehaviorService;

    @Autowired
    private SessionUtil sessionUtil;

    @GetMapping("/search")
    public ResultVo search(
            @RequestParam(name = "keywords") String keywords,
            @RequestParam(name = "pageNo") Integer pageNo,
            @RequestParam(name = "pageSize") Integer pageSize,
            HttpServletRequest request) {
        List<ArticleES> articleList = articleESService.search(keywords, pageNo, pageSize);
        userBehaviorService.searchArticles(sessionUtil.getUser(request).getId(), articleList, pageNo, pageSize);
        return ResultUtil.success(articleList);
    }

    @GetMapping("/addArticle")
    public ResultVo addArticle(@RequestParam(name = "count") int count) {
        articleService.addArticle(count);
        return ResultUtil.success();
    }


}

package cn.szsyph.dcd.controller;

import cn.szsyph.dcd.common.ResultUtil;
import cn.szsyph.dcd.common.ResultVo;
import cn.szsyph.dcd.repository.domain.*;
import cn.szsyph.dcd.service.UserGradeService;
import cn.szsyph.dcd.service.UserPinService;
import cn.szsyph.dcd.service.recommender.HotRecommenderService;
import cn.szsyph.dcd.service.recommender.ItemBasedSchedulerService;
import cn.szsyph.dcd.service.recommender.UserBasedSchedulerService;
import cn.szsyph.dcd.util.SessionUtil;
import cn.szsyph.dcd.vo.HomePageVo;
import org.apache.mahout.cf.taste.common.TasteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


/**
 * @Description 首页
 * @Author sunzhishang
 * @Version V1.0
 */
@RestController
@RequestMapping(value = "/motor/recommend")
public class RecommendController {

    @Autowired
    private SessionUtil sessionUtil;

    @Autowired
    private UserBasedSchedulerService userBasedSchedulerService;

    @Autowired
    private ItemBasedSchedulerService itemBasedSchedulerService;

    @Autowired
    private HotRecommenderService hotRecommenderService;

    @Autowired
    private UserPinService userPinService;

    @Autowired
    private UserGradeService userGradeService;

    /**
     * 获取首页数据
     */
    @GetMapping(value = {"/home"})
    public ResultVo home(
            @RequestParam(value = "pageNo") int pageNo,
            @RequestParam(value = "pageSize") int pageSize,
            HttpServletRequest request) throws TasteException {
        User user = sessionUtil.getUser(request);
        List<ArticleApi> userBasedApis = new ArrayList<>();
        List<ArticleApi> hotApis = new ArrayList<>();

        List<Article> userBasedRecommends = userBasedSchedulerService.recommend(user.getId(), pageSize);
        if (userBasedRecommends != null) {
            for (Article userBasedRecommend : userBasedRecommends) {
                ArticleApi articleApi = new ArticleApi(userBasedRecommend);
                articleApi.setIdStr(Long.toString(userBasedRecommend.getId()));
                if (user.getId() != 0) {
                    UserPin userPin = userPinService.getUserPinByUserIdAndArticleId(user.getId(), articleApi.getId());
                    if (userPin != null && userPin.getArticleId() != 0) {
                        articleApi.setPined(true);
                    }
                    UserGrade userGrade = userGradeService.getUserGradeByUserIdAndArticleId(user.getId(), articleApi.getId());
                    if (userGrade != null && userGrade.getArticleId() != 0) {
                        articleApi.setGrade(userGrade.getGrade());
                    }
                }
                userBasedApis.add(articleApi);
            }
        }


        List<Article> hotRecommends = hotRecommenderService.recommend(user.getId(), pageNo, pageSize);
        if (hotRecommends != null) {
            for (Article hotRecommend : hotRecommends) {
                ArticleApi articleApi = new ArticleApi(hotRecommend);
                articleApi.setIdStr(Long.toString(hotRecommend.getId()));
                if (user.getId() != 0) {
                    UserPin userPinByUserIdAndArticleId = userPinService.getUserPinByUserIdAndArticleId(user.getId(), articleApi.getId());
                    if (userPinByUserIdAndArticleId != null && userPinByUserIdAndArticleId.getArticleId() != 0) {
                        articleApi.setPined(true);
                    }
                    UserGrade userGrade = userGradeService.getUserGradeByUserIdAndArticleId(user.getId(), articleApi.getId());
                    if (userGrade != null && userGrade.getArticleId() != 0) {
                        articleApi.setGrade(userGrade.getGrade());
                    }
                }
                hotApis.add(articleApi);
            }
        }


        HomePageVo result = new HomePageVo(null, userBasedApis, hotApis);
        return ResultUtil.success(result);
    }

    @GetMapping(value = "/evaluation")
    public ResultVo listEvaluationByType(@RequestParam(value = "recommendType") Integer recommendType,
                                         @RequestParam(value = "pageNo", required = false, defaultValue = "1") Long pageNo,
                                         @RequestParam(value = "pageSize", required = false, defaultValue = "10") Long pageSize,
                                         HttpServletRequest request) throws TasteException {
        return ResultUtil.success();
    }


}

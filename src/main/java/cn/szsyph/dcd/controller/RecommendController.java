package cn.szsyph.dcd.controller;

import cn.szsyph.dcd.common.ResultUtil;
import cn.szsyph.dcd.common.ResultVo;
import cn.szsyph.dcd.constant.Constant;
import cn.szsyph.dcd.repository.domain.User;
import cn.szsyph.dcd.service.recommender.HotRecommenderService;
import cn.szsyph.dcd.service.recommender.ItemBasedSchedulerService;
import cn.szsyph.dcd.service.recommender.UserBasedSchedulerService;
import cn.szsyph.dcd.util.SessionUtil;
import cn.szsyph.dcd.vo.HomePageVo;
import org.apache.mahout.cf.taste.common.TasteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


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

    /**
     * 获取首页数据
     */
    @GetMapping(value = {"/home"})
    public ResultVo home(HttpServletRequest request) throws TasteException {
        User user = sessionUtil.getUser(request);
        HomePageVo result = new HomePageVo(itemBasedSchedulerService.recommend(user.getId(), Constant.DEFAULT_RECOMMEND_NUM), userBasedSchedulerService.recommend(user.getId(), Constant.DEFAULT_RECOMMEND_NUM), hotRecommenderService.recommend(user.getId(), Constant.DEFAULT_RECOMMEND_NUM));
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

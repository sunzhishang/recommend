package cn.szsyph.dcd.controller;

import cn.szsyph.dcd.common.ResultUtil;
import cn.szsyph.dcd.common.ResultVo;
import cn.szsyph.dcd.enums.ErrorEnum;
import cn.szsyph.dcd.repository.domain.User;
import cn.szsyph.dcd.service.UserBehaviorService;
import cn.szsyph.dcd.util.SessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description 用于行为控制器
 * @Author sunzhishang
 * @Version V1.0
 */
@Slf4j
@RestController
@RequestMapping(value = "/motor/behavior/")
public class UserBehaviorController {

    @Autowired
    private SessionUtil sessionUtil;

    @Autowired
    private UserBehaviorService userBehaviorService;

    @GetMapping(value = "/click")
    public ResultVo click(@RequestParam(value = "article_id") long articleId, HttpServletRequest request) {
        if (articleId <= 0) {
            log.error("MicroscopeController#click: param is illegal. articleId={}", articleId);
            return ResultUtil.error(ErrorEnum.PARAM_ILLEGAL);
        }
        User user = sessionUtil.getUser(request);
        if (user.getId() == 0) {
            return ResultUtil.error(ErrorEnum.NO_USER);
        }
        userBehaviorService.clickArticle(user.getId(), articleId);
        return ResultUtil.success();
    }

    @GetMapping(value = "/grade")
    public ResultVo grade(@RequestParam(value = "article_id") long articleId,
                          @RequestParam(value = "grade") int grade, HttpServletRequest request) {
        if (articleId <= 0) {
            log.error("MicroscopeController#grade: param is illegal. articleId={}", articleId);
            return ResultUtil.error(ErrorEnum.PARAM_ILLEGAL);
        }
        User user = sessionUtil.getUser(request);
        if (user.getId() == 0) {
            return ResultUtil.error(ErrorEnum.NO_USER);
        }
        userBehaviorService.gradeArticle(user.getId(), articleId,grade);
        return ResultUtil.success();
    }

    @GetMapping(value = "/pin")
    public ResultVo pin(@RequestParam(value = "article_id") long articleId, HttpServletRequest request) {
        if (articleId <= 0) {
            log.error("MicroscopeController#pin: param is illegal. articleId={}", articleId);
            return ResultUtil.error(ErrorEnum.PARAM_ILLEGAL);
        }
        User user = sessionUtil.getUser(request);
        if (user.getId() == 0) {
            return ResultUtil.error(ErrorEnum.NO_USER);
        }
        userBehaviorService.pinArticle(user.getId(), articleId);
        return ResultUtil.success();
    }

    @GetMapping(value = "/cancel_pin")
    public ResultVo cancelPin(@RequestParam(value = "article_id") long articleId, HttpServletRequest request) {
        if (articleId == 0) {
            log.error("MicroscopeController#cancel_pin: param is illegal. articleId={}", articleId);
            return ResultUtil.error(ErrorEnum.PARAM_ILLEGAL);
        }
        User user = sessionUtil.getUser(request);
        if (user.getId() == 0) {
            return ResultUtil.error(ErrorEnum.NO_USER);
        }
        userBehaviorService.cancelPinArticle(user.getId(), articleId);
        return ResultUtil.success();
    }


}

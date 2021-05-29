package cn.szsyph.dcd.util;

import cn.szsyph.dcd.repository.domain.User;
import cn.szsyph.dcd.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Description session相关操作
 * @Author sunzhishang
 **/
@Slf4j
@Service
public class SessionUtil {


    @Autowired
    private UserService userService;


    public static void putUserId(HttpServletRequest request, Long userId) {
        HttpSession session = request.getSession(true);
        session.setAttribute("user_id", userId);
    }

    /**
     * 获取用户基本信息
     */
    public User getUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user_id") == null) {
            return new User();
        }
        return userService.getUserById((Long) (session.getAttribute("user_id")));
//        return new User(4, "sunzhishang", "123456");
    }
}

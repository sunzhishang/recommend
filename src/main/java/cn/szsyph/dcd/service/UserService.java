package cn.szsyph.dcd.service;

import cn.szsyph.dcd.repository.dao.UserDao;
import cn.szsyph.dcd.repository.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public User getUserById(Long userId) {
        Optional<User> byId = userDao.findById((long) userId);
        return byId.orElseGet(User::new);
    }

    public User getUserByUserNameAndPassword(String username, String password) {
       return  userDao.findByUsernameAndPassword(username, password);
    }

    public void register(User user){
        userDao.save(user);
    }
}

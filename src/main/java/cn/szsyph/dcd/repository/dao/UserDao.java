package cn.szsyph.dcd.repository.dao;


import cn.szsyph.dcd.repository.domain.User;

/**
 * @Description 用户基本信息DAO
 * @Author sunzhishang
 **/
public interface UserDao extends org.springframework.data.jpa.repository.JpaRepository<User, Long> {
    public User findByUsernameAndPassword(String username, String password);
}

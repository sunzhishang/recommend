package cn.szsyph.dcd.repository.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;

/**
 * @Description 用户基本信息
 * @Author sunzhishang
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User implements Serializable {
    private static final long serialVersionUID = 4044277484420982044L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "user_name")
    private String username;

    @Column(name = "password")
    private String password;

}

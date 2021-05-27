package cn.szsyph.dcd.repository.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;

/**
 * @Description 用户偏好
 * @Author sunzhishang
 * @Version V1.0
 */



@IdClass(value = RelationPK.class)
@Entity(name = "user_preference")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPreference implements Serializable {

    private static final long serialVersionUID = 181230263854707171L;


    @Id
    @Column(name = "user_id")
    private long userId;

    @Id
    @Column(name = "article_id")
    private long articleId;

    @Column(name = "preference")
    private double preference;


}

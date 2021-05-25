package cn.szsyph.dcd.repository.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @Description 用户行为表
 * @Author sunzhishang
 * @Version V1.0
 */

@IdClass(RelationPK.class)
@Entity(name = "user_pin")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPin implements Serializable {

    private static final long serialVersionUID = 1616767221060293312L;

    @Id
    @Column(name = "user_id")
    private long userId;

    @Id
    @Column(name = "article_id")
    private long articleId;

    @Column(name = "modify_time")
    @Generated(GenerationTime.ALWAYS)
    private Timestamp modify_time;
}

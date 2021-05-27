package cn.szsyph.dcd.repository.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@IdClass(RelationPK.class)
@Entity(name = "user_click")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserClick implements Serializable {
    private static final long serialVersionUID = 8617976254063145548L;

    @Id
    @Column(name = "user_id")
    private long userId;

    @Id
    @Column(name = "article_id")
    private long articleId;

    @Column(name = "modify_time")
    @Generated(GenerationTime.ALWAYS)
    private Timestamp modifyTime;


}

package cn.szsyph.dcd.repository.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Entity(name = "user_grade")
@IdClass(RelationPK.class)
@AllArgsConstructor
@NoArgsConstructor
public class UserGrade implements Serializable {

    private static final long serialVersionUID = -1672807711232837833L;
    @Id
    @Column(name = "user_id")
    private long userId;

    @Id
    @Column(name = "article_id")
    private long articleId;

    @Column(name = "grade")
    private int grade;

    @Column(name = "modify_time")
    @Generated(GenerationTime.ALWAYS)
    private Timestamp modifyTime;
}

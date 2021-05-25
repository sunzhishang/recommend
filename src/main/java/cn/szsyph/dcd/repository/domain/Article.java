package cn.szsyph.dcd.repository.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;

/**
 * @Description 推荐器评估
 * @Author sunzhishang
 * @Version V1.0
 */

@Entity(name = "article")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article implements Serializable {
    private static final long serialVersionUID = -7962457506351103183L;

    @Id
    private long Id;

    @Column(name = "title")
    private String title;

    @Column(name = "image")
    private String image;

    @Column(name = "content")
    private String content;

    @Column(name = "is_video")
    private boolean isVideo;
}

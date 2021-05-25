package cn.szsyph.dcd.vo;

import cn.szsyph.dcd.repository.domain.Article;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HomePageVo implements Serializable {
    private static final long serialVersionUID = 1216744920000809794L;

    private List<Article> itemBasedRecommendation;
    private List<Article> userBasedRecommendation;
    private List<Article> hotRecommendation;

}

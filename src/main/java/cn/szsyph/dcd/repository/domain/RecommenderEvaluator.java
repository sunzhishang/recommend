package cn.szsyph.dcd.repository.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.bind.Name;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Description 推荐器评估
 * @Author sunzhishang
 * @Version V1.0
 */
@Entity(name = "recommender_evaluator")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecommenderEvaluator implements Serializable {

    private static final long serialVersionUID = 814619240226812791L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    /**
     * 评分
     */

    @Column(name = "score")
    private Double score;

    /**
     * 查准率
     */
    @Column(name = "precision")
    private Double precision;

    /**
     * 召回率、查全率
     */

    @Column(name = "recall")
    private Double recall;

    /**
     * 推荐类型
     */
    @Column(name = "type")
    private Integer type;


}

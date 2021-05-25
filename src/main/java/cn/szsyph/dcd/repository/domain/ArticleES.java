package cn.szsyph.dcd.repository.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;



@Document(indexName = "article")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleES {
    @Id
    private long id;
    @Field(analyzer = "ik_smart", type = FieldType.Text)
    private String title;
    @Field(analyzer = "ik_smart", type = FieldType.Text)
    private String image;
    @Field(analyzer = "ik_smart", type = FieldType.Text)
    private String content;


}

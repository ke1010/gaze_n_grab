package app.gazengrab.org.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "categories")
public class Category {
    @Id
    private String id;
    private String name;

    @Field("imgUrl")
    private String imageUrl;
}

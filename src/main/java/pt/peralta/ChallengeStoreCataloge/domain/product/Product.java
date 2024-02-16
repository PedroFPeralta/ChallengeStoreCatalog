package pt.peralta.ChallengeStoreCataloge.domain.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import pt.peralta.ChallengeStoreCataloge.domain.category.Category;

@Document(collection = "products")
@Getter
@Setter
@NoArgsConstructor
public class Product {

    @Id
    private String id;
    private String tilte;
    private String description;
    private String ownerId;
    private Integer price;
    private Category category;
}

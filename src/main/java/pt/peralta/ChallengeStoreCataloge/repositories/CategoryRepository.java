package pt.peralta.ChallengeStoreCataloge.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pt.peralta.ChallengeStoreCataloge.domain.category.Category;

@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {
}

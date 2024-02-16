package pt.peralta.ChallengeStoreCataloge.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pt.peralta.ChallengeStoreCataloge.domain.product.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
}

package pt.peralta.ChallengeStoreCataloge.services;

import org.springframework.stereotype.Service;
import pt.peralta.ChallengeStoreCataloge.domain.category.Category;
import pt.peralta.ChallengeStoreCataloge.domain.category.exceptions.CategoryNotFoundException;
import pt.peralta.ChallengeStoreCataloge.domain.product.Product;
import pt.peralta.ChallengeStoreCataloge.domain.product.ProductDTO;
import pt.peralta.ChallengeStoreCataloge.domain.product.exceptions.ProductNotFoundException;
import pt.peralta.ChallengeStoreCataloge.repositories.ProductRepository;

import java.util.List;

@Service
public class ProductService {

    private CategoryService categoryService;
    private ProductRepository repository;

    public ProductService(CategoryService categoryService, ProductRepository productRepository) {
        this.categoryService = categoryService;
        this.repository = productRepository;
    }

    public Product create(ProductDTO productDTO){
        //Verify if category exists
        Category category = categoryService.getById(productDTO.categoryId())
                .orElseThrow(CategoryNotFoundException::new);

        Product newProduct = new Product(productDTO);

        //Insert Category in product
        newProduct.setCategory(category);

        this.repository.save(newProduct);

        return newProduct;
    }

    public List<Product> listAll() {
        return this.repository.findAll();
    }

    public Product update(String id, ProductDTO productDTO){
        Product toUpdate = this.repository.findById(id)
                .orElseThrow(ProductNotFoundException::new);

        //If Category exist then update
        categoryService.getById(productDTO.categoryId())
                .ifPresent(toUpdate::setCategory);

        if(!productDTO.title().isEmpty())
            toUpdate.setTitle(productDTO.title());
        if(!productDTO.description().isEmpty())
            toUpdate.setDescription(productDTO.description());
        if(productDTO.price() != null)
            toUpdate.setPrice(productDTO.price());

        this.repository.save(toUpdate);
        return toUpdate;
    }


    public void delete(String id) {
        Product toUpdate = this.repository.findById(id).orElseThrow(ProductNotFoundException::new);

        this.repository.delete(toUpdate);
    }

}

package pt.peralta.ChallengeStoreCataloge.services;

import org.springframework.stereotype.Service;
import pt.peralta.ChallengeStoreCataloge.domain.category.Category;
import pt.peralta.ChallengeStoreCataloge.domain.category.CategoryDTO;
import pt.peralta.ChallengeStoreCataloge.domain.category.exceptions.CategoryNotFoundException;
import pt.peralta.ChallengeStoreCataloge.repositories.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public Category create(CategoryDTO categoryDTO){
        Category newCategory = new Category(categoryDTO);
        this.repository.save(newCategory);

        return newCategory;
    }

    public List<Category> listAll() {
        return this.repository.findAll();
    }

    public Category update(String id, CategoryDTO categoryDTO){
        Category toUpdate = this.repository.findById(id).orElseThrow(CategoryNotFoundException::new);

        if(!categoryDTO.title().isEmpty())
            toUpdate.setTitle(categoryDTO.title());
        if(!categoryDTO.description().isEmpty())
            toUpdate.setDescription(categoryDTO.description());

        this.repository.save(toUpdate);
        return toUpdate;
    }


    public void delete(String id) {
        Category toUpdate = this.repository.findById(id).orElseThrow(CategoryNotFoundException::new);

        this.repository.delete(toUpdate);
    }

    public Optional<Category> getById(String id){
        return this.repository.findById(id);
    }
}

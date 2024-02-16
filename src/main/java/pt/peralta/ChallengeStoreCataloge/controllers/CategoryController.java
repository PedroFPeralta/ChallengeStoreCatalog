package pt.peralta.ChallengeStoreCataloge.controllers;

import jakarta.websocket.server.PathParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.peralta.ChallengeStoreCataloge.domain.category.Category;
import pt.peralta.ChallengeStoreCataloge.domain.category.CategoryDTO;
import pt.peralta.ChallengeStoreCataloge.services.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Category> create(@RequestBody CategoryDTO category){
        Category newCategory = this.service.create(category);

        return ResponseEntity.ok().body(newCategory);
    }

    @GetMapping
    public ResponseEntity<List<Category>> listAll(){
        return ResponseEntity.ok().body(this.service.listAll());
    }

    @PutMapping("{id}")
    public ResponseEntity<Category> update(@PathParam("id") String id, @RequestBody CategoryDTO category){
        Category toUpdate = this.service.update(id,category);
        return ResponseEntity.ok().body(toUpdate);
    }

    @DeleteMapping ("{id}")
    public ResponseEntity<Category> delete(@PathParam("id") String id){
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }

}

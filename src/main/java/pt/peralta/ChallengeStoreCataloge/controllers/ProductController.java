package pt.peralta.ChallengeStoreCataloge.controllers;

import jakarta.websocket.server.PathParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.peralta.ChallengeStoreCataloge.domain.product.Product;
import pt.peralta.ChallengeStoreCataloge.domain.product.ProductDTO;
import pt.peralta.ChallengeStoreCataloge.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody ProductDTO productDTO){
        Product newProduct = this.service.create(productDTO);

        return ResponseEntity.ok().body(newProduct);
    }

    @GetMapping
    public ResponseEntity<List<Product>> listAll(){
        return ResponseEntity.ok().body(this.service.listAll());
    }

    @PutMapping("{id}")
    public ResponseEntity<Product> update(@PathParam("id") String id, @RequestBody ProductDTO productDTO){
        Product toUpdate = this.service.update(id,productDTO);
        return ResponseEntity.ok().body(toUpdate);
    }

    @DeleteMapping ("{id}")
    public ResponseEntity<Product> delete(@PathParam("id") String id){
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }

}

package atom.darkstore.Controller;

import atom.darkstore.Entity.Product;
import atom.darkstore.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/all")
    public List<Product> getAllProducts(){
        return productService.getProductAll();
    }

    @GetMapping("/{id}")
    public Object getProductById(@PathVariable int id){
        return productService.getProductById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createProduct(@RequestBody Product product){
        System.out.println(product.toString());
        return productService.createProduct(product);
    }

    @DeleteMapping("/update/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable int id, @RequestBody Product product){
        return productService.updateProduct(product, id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id){
        return productService.deleteProduct(id);
    }
}

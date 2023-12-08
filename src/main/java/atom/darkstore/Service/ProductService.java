package atom.darkstore.Service;

import atom.darkstore.Entity.Product;
import atom.darkstore.Repo.ProductRepo;
import atom.darkstore.Repo.ShelfRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private ShelfRepo shelfRepo;

    public List<Product> getProductAll(){
        return productRepo.findAll();
    }

    public Object getProductById(int id){
        if(productRepo.findById(id).isEmpty()){
            return new ResponseEntity<>("No such product with this id in database", HttpStatusCode.valueOf(400));
        }
        return productRepo.getReferenceById(id);
    }

    public ResponseEntity<String> createProduct(Product product){
        if(productRepo.findByName(product.getName()).isPresent()){
            return new ResponseEntity<>("Product with this name is already exists", HttpStatusCode.valueOf(400));
        }
        if(shelfRepo.findById(product.getIdShelf()).isEmpty()){
            return new ResponseEntity<>("No such shelf with this id in database", HttpStatusCode.valueOf(400));
        }
        productRepo.saveAndFlush(product);
        return new ResponseEntity<>("Successfully added", HttpStatusCode.valueOf(400));
    }

    public ResponseEntity<String> updateProduct(Product product, int id){
        if(!productRepo.existsById(id)){
            return new ResponseEntity<>("No such product with this id in database", HttpStatusCode.valueOf(400));
        }
        Product oldProduct = productRepo.getReferenceById(id);
        oldProduct.setName(product.getName());
        oldProduct.setPrice(product.getPrice());
        oldProduct.setQuantity(product.getQuantity());
        oldProduct.setIdShelf(product.getIdShelf());

        productRepo.saveAndFlush(oldProduct);
        return new ResponseEntity<>("Successfully updated", HttpStatusCode.valueOf(400));
    }

    public ResponseEntity<String> deleteProduct(int id){
        if(!productRepo.existsById(id)){
            return new ResponseEntity<>("No such product with this id in database", HttpStatusCode.valueOf(400));
        }
        productRepo.deleteById(id);
        return new ResponseEntity<>("Successfully deleted", HttpStatusCode.valueOf(400));
    }
}

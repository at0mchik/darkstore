package atom.darkstore.Service;

import atom.darkstore.Entity.OrderHasProducts;
import atom.darkstore.Entity.Product;
import atom.darkstore.Repo.OhpRepo;
import atom.darkstore.Repo.OrderRepo;
import atom.darkstore.Repo.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OhpService {
    @Autowired
    private OhpRepo ohpRepo;
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private ProductRepo productRepo;
    private final ProductService productService;

    public List<OrderHasProducts> getOhpAll(){
        return ohpRepo.findAll();
    }

    public Object getOhpById(int id){
        if(ohpRepo.findById(id).isEmpty()){
            return new ResponseEntity<>("No such ohp-stroke with this id in database", HttpStatusCode.valueOf(400));
        }
        return ohpRepo.getReferenceById(id);
    }

    public ResponseEntity<String> createOhp(OrderHasProducts ohp){
        if(productRepo.findById(ohp.getIdProduct()).isEmpty()){
            return new ResponseEntity<>("No such product with this id in database", HttpStatusCode.valueOf(400));
        }
        else if(orderRepo.findById(ohp.getIdOrder()).isEmpty()){
            return new ResponseEntity<>("No such order with this id in database", HttpStatusCode.valueOf(400));
        }
        else {
            Product product = (Product) productService.getProductById(ohp.getIdProduct());
            if (product.getQuantity() < ohp.getProductQuantityOrder()){
                return new ResponseEntity<>("You cannot add this amount of product (there is lower amount on shelf)", HttpStatusCode.valueOf(400));
            }
            else {
                product.setQuantity(product.getQuantity() - ohp.getProductQuantityOrder());
                productService.updateProduct(product, ohp.getIdProduct());
            }
        }

        ohpRepo.saveAndFlush(ohp);
        return new ResponseEntity<>("Successfully added", HttpStatusCode.valueOf(400));
    }

    public ResponseEntity<String> deleteOhp(int id){
        if(!ohpRepo.existsById(id)){
            return new ResponseEntity<>("No such ohp-stroke with this id in database", HttpStatusCode.valueOf(400));
        }
        ohpRepo.deleteById(id);
        return new ResponseEntity<>("Successfully deleted", HttpStatusCode.valueOf(400));
    }
}

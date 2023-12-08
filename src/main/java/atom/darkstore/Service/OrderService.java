package atom.darkstore.Service;

import atom.darkstore.Entity.Order;
import atom.darkstore.Entity.Product;
import atom.darkstore.Repo.OrderRepo;
import atom.darkstore.Repo.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    @Autowired
    private OrderRepo orderRepo;

    public List<Order> getOrderAll(){
        return orderRepo.findAll();
    }

    public Object getOrderById(int id){
        if(orderRepo.findById(id).isEmpty()){
            return new ResponseEntity<>("No such order with this id in database", HttpStatusCode.valueOf(400));
        }
        return orderRepo.getReferenceById(id);
    }

    public ResponseEntity<String> createOrder(Order order){

        orderRepo.saveAndFlush(order);
        return new ResponseEntity<>("Successfully added", HttpStatusCode.valueOf(400));
    }

    public Object getOrderByAddress(String clientAddress){
        if(orderRepo.findByClientAddress(clientAddress).isEmpty()){
            return new ResponseEntity<>("No such order with this address in database", HttpStatusCode.valueOf(400));
        }
        return orderRepo.findByClientAddress(clientAddress);
    }
    public ResponseEntity<String> deleteOrder(int id){
        if(!orderRepo.existsById(id)){
            return new ResponseEntity<>("No such order with this id in database", HttpStatusCode.valueOf(400));
        }
        orderRepo.deleteById(id);
        return new ResponseEntity<>("Successfully deleted", HttpStatusCode.valueOf(400));
    }
}

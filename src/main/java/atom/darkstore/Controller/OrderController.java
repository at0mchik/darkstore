package atom.darkstore.Controller;

import atom.darkstore.Entity.Order;
import atom.darkstore.Entity.Product;
import atom.darkstore.Service.OrderService;
import atom.darkstore.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/all")
    public List<Order> getAllOrders(){
        return orderService.getOrderAll();
    }

    @GetMapping("/{id}")
    public Object getOrderById(@PathVariable int id){
        return orderService.getOrderById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createOrder(@RequestBody Order order){
        System.out.println(order.toString());
        return orderService.createOrder(order);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable int id){
        return orderService.deleteOrder(id);
    }
}

package atom.darkstore.Controller;

import atom.darkstore.DTO.CreateFullOrderRequest;
import atom.darkstore.DTO.CreateProductsRequest;
import atom.darkstore.DTO.CreateShelvesRequest;
import atom.darkstore.Entity.Order;
import atom.darkstore.Entity.OrderHasProducts;
import atom.darkstore.Entity.Product;
import atom.darkstore.Entity.Shelf;
import atom.darkstore.Service.OhpService;
import atom.darkstore.Service.OrderService;
import atom.darkstore.Service.ProductService;
import atom.darkstore.Service.ShelfService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/functions")
@RequiredArgsConstructor
public class FunctionController {
    private final OhpService ohpService;
    private final OrderService orderService;
    private final ProductService productService;
    private final ShelfService shelfService;

    @PostMapping("/addShelves")
    public ResponseEntity<String> addShelves(@RequestBody CreateShelvesRequest createShelvesRequest){
        if(createShelvesRequest.getShelvesNamesList().size() != createShelvesRequest.getShelvesTypesList().size()){
            return new ResponseEntity<>("Size of lists does not match", HttpStatusCode.valueOf(400));
        }
        ResponseEntity<String> errorResponse = new ResponseEntity<>("Shelf with this name is already exists", HttpStatusCode.valueOf(400));
        for(int i = 0; i < createShelvesRequest.getShelvesNamesList().size(); i++){
            Shelf tempShelf = new Shelf();
            tempShelf.setName(createShelvesRequest.getShelvesNamesList().get(i));
            tempShelf.setType(createShelvesRequest.getShelvesTypesList().get(i));
            ResponseEntity<String> checkResponse = shelfService.createShelf(tempShelf);
            if(checkResponse == errorResponse){
                return new ResponseEntity<>("Shelf with name" + createShelvesRequest.getShelvesNamesList().get(i) + "is already exists", HttpStatusCode.valueOf(400));
            }
        }
        return new ResponseEntity<>("All shelves have been added", HttpStatusCode.valueOf(400));
    }

    @PostMapping("/addOrder")
    public ResponseEntity<String> addNewOrder(@RequestBody CreateFullOrderRequest createFullOrderRequest){
        if(createFullOrderRequest.getListOfProductId().size() != createFullOrderRequest.getListOfProductQuantity().size()){
            return new ResponseEntity<>("Size of lists does not match", HttpStatusCode.valueOf(400));
        }
//        ResponseEntity<String> successResponse = new ResponseEntity<>("Successfully added", HttpStatusCode.valueOf(400));
        ResponseEntity<String> errorResponseProduct = new ResponseEntity<>("No such product with this id in database", HttpStatusCode.valueOf(400));
        ResponseEntity<String> errorResponseOrder = new ResponseEntity<>("No such order with this id in database", HttpStatusCode.valueOf(400));
        ResponseEntity<String> errorResponseQuantity =  new ResponseEntity<>("You cannot add this amount of product (there is lower amount on shelf)", HttpStatusCode.valueOf(400));

        Order newOrder = new Order();
        newOrder.setTimeStart(createFullOrderRequest.getTimeStartOrder());
        newOrder.setClientAddress(createFullOrderRequest.getClientAddressOrder());
        orderService.createOrder(newOrder);

        List<Order> orderList = orderService.getOrderAll();
        int orderId = orderList.get(orderList.size()-1).getId();

        if (orderId >= 0) {
            for (int i = 0; i < createFullOrderRequest.getListOfProductId().size(); i++) {
                OrderHasProducts newOhp = new OrderHasProducts();
                newOhp.setIdOrder(orderId);
                newOhp.setIdProduct(createFullOrderRequest.getListOfProductId().get(i));
                newOhp.setProductQuantityOrder(createFullOrderRequest.getListOfProductQuantity().get(i));
                ResponseEntity<String> checkResponse = ohpService.createOhp(newOhp);
                if(checkResponse == errorResponseProduct || checkResponse == errorResponseOrder || checkResponse == errorResponseQuantity){
                    return checkResponse;
                }
            }
        }

        return new ResponseEntity<String>("Order has been successfully added", HttpStatusCode.valueOf(400));
    }


    @PostMapping("/addProducts")
    public ResponseEntity<String> addProducts(@RequestBody CreateProductsRequest createProductsRequest){
        if(!(createProductsRequest.getListOfProductName().size() == createProductsRequest.getListOfProductPrice().size() &&
                createProductsRequest.getListOfProductName().size() == createProductsRequest.getListOfProductQuantity().size() &&
                createProductsRequest.getListOfProductName().size() == createProductsRequest.getListOfProductShelfId().size())){
            return new ResponseEntity<String>("Size of lists does not match", HttpStatusCode.valueOf(400));
        }

        ResponseEntity<String> errorResponseName = new ResponseEntity<>("Product with this name is already exists", HttpStatusCode.valueOf(400));
        ResponseEntity<String> errorResponseShelf = new ResponseEntity<>("No such shelf with this id in database", HttpStatusCode.valueOf(400));

        for(int i = 0; i < createProductsRequest.getListOfProductName().size(); i++){
            Product newProduct = new Product();
            newProduct.setName(createProductsRequest.getListOfProductName().get(i));
            newProduct.setPrice(createProductsRequest.getListOfProductPrice().get(i));
            newProduct.setQuantity(createProductsRequest.getListOfProductQuantity().get(i));
            newProduct.setIdShelf(createProductsRequest.getListOfProductShelfId().get(i));

            ResponseEntity<String> checkResponse = productService.createProduct(newProduct);
            if(checkResponse == errorResponseName || checkResponse == errorResponseShelf){
                return checkResponse;
            }
        }

        return new ResponseEntity<>("Products have been successfully added", HttpStatusCode.valueOf(400));
    }
}

package atom.darkstore;

import atom.darkstore.Controller.OrderController;
import atom.darkstore.Service.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {
    @Mock
    private OrderService orderService;
    @InjectMocks
    private OrderController orderController;

    @Test
    void getAllOrders(){
        System.out.println();

        System.out.println();
    }

    @Test
    void createOrder(){
        System.out.println();

        System.out.println();
    }

    @Test
    void deleteOrder(){
        System.out.println();

        System.out.println();
    }
}

package atom.darkstore.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Component
@Table(name = "order_has_products")
public class OrderHasProducts {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    int idOrder;
    int idProduct;
    int productQuantityOrder;
}

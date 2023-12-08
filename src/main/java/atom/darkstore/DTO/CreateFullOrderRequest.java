package atom.darkstore.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateFullOrderRequest {
    private String timeStartOrder;
    private String clientAddressOrder;
    private List<Integer> listOfProductId;
    private List<Integer> listOfProductQuantity;
}

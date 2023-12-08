package atom.darkstore.DTO;

import atom.darkstore.Entity.typeShelves;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateProductsRequest {
    private List<String > listOfProductName;
    private List<Integer> listOfProductPrice;
    private List<Integer> listOfProductQuantity;
    private List<Integer> listOfProductShelfId;
}

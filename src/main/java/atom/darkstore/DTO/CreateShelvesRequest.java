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
public class CreateShelvesRequest {
    private List<String> shelvesNamesList;
    private List<typeShelves> shelvesTypesList;
}

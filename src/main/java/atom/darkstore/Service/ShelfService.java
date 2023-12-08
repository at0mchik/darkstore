package atom.darkstore.Service;

import atom.darkstore.Entity.Shelf;
import atom.darkstore.Repo.ShelfRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShelfService {
    @Autowired
    private ShelfRepo shelfRepo;

    public List<Shelf> getShelfAll(){
        return shelfRepo.findAll();
    }

    public Object getShelfById(int id){
        if(shelfRepo.findById(id).isEmpty()){
            return new ResponseEntity<>("No such shelf with this id in database", HttpStatusCode.valueOf(400));
        }
        return shelfRepo.getReferenceById(id);
    }

    public ResponseEntity<String> createShelf(Shelf shelf){
        if(shelfRepo.findByName(shelf.getName()).isPresent()){
            return new ResponseEntity<>("Shelf with this name is already exists", HttpStatusCode.valueOf(400));
        }
        shelfRepo.saveAndFlush(shelf);
        return new ResponseEntity<>("Successfully added", HttpStatusCode.valueOf(400));
    }

    public ResponseEntity<String> deleteShelf(int id){
        if(!shelfRepo.existsById(id)){
            return new ResponseEntity<>("No such shelf with this id in database", HttpStatusCode.valueOf(400));
        }
        shelfRepo.deleteById(id);
        return new ResponseEntity<>("Successfully deleted", HttpStatusCode.valueOf(400));
    }
}

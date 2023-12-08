package atom.darkstore.Controller;

import atom.darkstore.Entity.Shelf;
import atom.darkstore.Service.ShelfService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shelf")
@RequiredArgsConstructor
public class ShelfController {
    private final ShelfService shelfService;

    @GetMapping("/all")
    public List<Shelf> getAllShelves(){
        return shelfService.getShelfAll();
    }

    @GetMapping("/{id}")
    public Object getShelfById(@PathVariable int id){
        return shelfService.getShelfById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createShelf(@RequestBody Shelf shelf){
        System.out.println(shelf.toString());
        return shelfService.createShelf(shelf);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteShelf(@PathVariable int id){
        return shelfService.deleteShelf(id);
    }
}

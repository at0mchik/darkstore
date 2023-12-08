package atom.darkstore.Controller;

import atom.darkstore.Entity.Order;
import atom.darkstore.Entity.OrderHasProducts;
import atom.darkstore.Service.OhpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ohp")
@RequiredArgsConstructor
public class OhpController {
    private final OhpService ohpService;

    @GetMapping("/all")
    public List<OrderHasProducts> getAllOhp(){
        return ohpService.getOhpAll();
    }

    @GetMapping("/{id}")
    public Object getOhpById(@PathVariable int id){
        return ohpService.getOhpById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createOhp(@RequestBody OrderHasProducts ohp){
        System.out.println(ohp.toString());
        return ohpService.createOhp(ohp);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteOhp(@PathVariable int id){
        return ohpService.deleteOhp(id);
    }
}

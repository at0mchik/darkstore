package atom.darkstore.Repo;

import atom.darkstore.Entity.OrderHasProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OhpRepo extends JpaRepository<OrderHasProducts, Integer> {
    Optional<OrderHasProducts> findByIdOrder(int id);
}

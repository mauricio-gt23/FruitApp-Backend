package back.fruitback.services.domain.persistence;

import back.fruitback.services.domain.model.Fruit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FruitRepository extends JpaRepository<Fruit, Long> {
    Boolean existsByName(String name);
    List<Fruit> findByNameContainingIgnoreCase(String name);
}

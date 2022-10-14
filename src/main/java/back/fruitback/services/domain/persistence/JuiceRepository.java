package back.fruitback.services.domain.persistence;

import back.fruitback.services.domain.model.Juice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JuiceRepository extends JpaRepository<Juice, Long> {
    Boolean existsByName(String name);
    List<Juice> findByNameContainingIgnoreCase(String name);
}

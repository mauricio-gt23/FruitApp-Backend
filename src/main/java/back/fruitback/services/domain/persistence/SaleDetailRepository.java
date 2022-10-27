package back.fruitback.services.domain.persistence;

import back.fruitback.services.domain.model.SaleDetail;
import back.fruitback.services.domain.model.SaleDetailKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleDetailRepository extends JpaRepository<SaleDetail, SaleDetailKey> {
    List<SaleDetail> findBySaleId (Long saleId);
}

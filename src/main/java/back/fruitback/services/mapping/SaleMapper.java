package back.fruitback.services.mapping;

import back.fruitback.services.domain.model.Sale;
import back.fruitback.services.resource.SaleResource;
import back.fruitback.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class SaleMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;

    public Page<SaleResource> modelListToPage(List<Sale> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, SaleResource.class), pageable, modelList.size());
    }

    public SaleResource toResource(Sale model) {
        return mapper.map(model, SaleResource.class);
    }
}

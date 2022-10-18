package back.fruitback.services.mapping;

import back.fruitback.services.domain.model.Product;
import back.fruitback.services.resource.ProductResource;
import back.fruitback.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class ProductMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;

    public Page<ProductResource> modelListToPage(List<Product> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, ProductResource.class), pageable, modelList.size());
    }

    public ProductResource toResource(Product model) {
        return mapper.map(model, ProductResource.class);
    }

}

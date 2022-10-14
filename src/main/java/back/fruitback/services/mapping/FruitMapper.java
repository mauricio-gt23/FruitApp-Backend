package back.fruitback.services.mapping;

import back.fruitback.services.domain.model.Fruit;
import back.fruitback.services.resource.FruitResource;
import back.fruitback.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class FruitMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;

    public Page<FruitResource> modelListToPage(List<Fruit> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, FruitResource.class), pageable, modelList.size());
    }

    public FruitResource toResource(Fruit model) {
        return mapper.map(model, FruitResource.class);
    }
}

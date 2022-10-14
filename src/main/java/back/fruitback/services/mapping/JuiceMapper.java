package back.fruitback.services.mapping;

import back.fruitback.services.domain.model.Juice;
import back.fruitback.services.resource.JuiceResource;
import back.fruitback.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class JuiceMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;

    public Page<JuiceResource> modelListToPage(List<Juice> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, JuiceResource.class), pageable, modelList.size());
    }

    public JuiceResource toResource(Juice model) {
        return mapper.map(model, JuiceResource.class);
    }

}

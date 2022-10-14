package back.fruitback.services.mapping;

import back.fruitback.services.domain.model.SaleDetail;
import back.fruitback.services.resource.RegisterSaleDetailRequest;
import back.fruitback.services.resource.SaleDetailResource;
import back.fruitback.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class SaleDetailMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;

    public Page<SaleDetailResource> modelListToPage(List<SaleDetail> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, SaleDetailResource.class), pageable, modelList.size());
    }

    public SaleDetailResource toResource(SaleDetail model) {
        return mapper.map(model, SaleDetailResource.class);
    }

    public SaleDetail toModel(RegisterSaleDetailRequest request) {
        return mapper.map(request, SaleDetail.class);
    }
}

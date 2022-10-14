package back.fruitback.userProfile.mapping;

import back.fruitback.shared.mapping.EnhancedModelMapper;
import back.fruitback.userProfile.domain.model.Client;
import back.fruitback.userProfile.resource.ClientResource;
import org.springframework.beans.factory.annotation.Autowired;

public class ClientMapper {

    @Autowired
    EnhancedModelMapper mapper;

    public ClientResource toResource(Client model) {
        return mapper.map(model, ClientResource.class);
    }


}

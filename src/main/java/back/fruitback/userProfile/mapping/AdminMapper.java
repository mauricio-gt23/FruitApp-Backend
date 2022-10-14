package back.fruitback.userProfile.mapping;

import back.fruitback.shared.mapping.EnhancedModelMapper;
import back.fruitback.userProfile.domain.model.Admin;
import back.fruitback.userProfile.resource.AdminResource;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

public class AdminMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;

    public AdminResource toResource(Admin model) {
        return mapper.map(model, AdminResource.class);
    }

}

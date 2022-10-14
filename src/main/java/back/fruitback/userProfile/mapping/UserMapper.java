package back.fruitback.userProfile.mapping;

import back.fruitback.shared.mapping.EnhancedModelMapper;
import back.fruitback.userProfile.domain.model.User;
import back.fruitback.userProfile.resource.UserResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class UserMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;

    public Page<UserResource> modelListToPage(List<User> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, UserResource.class), pageable, modelList.size());
    }
}

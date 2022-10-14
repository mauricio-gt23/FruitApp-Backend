package back.fruitback.userProfile.controller;

import back.fruitback.shared.api.ApiController;
import back.fruitback.userProfile.domain.service.UserService;
import back.fruitback.userProfile.mapping.UserMapper;
import back.fruitback.userProfile.resource.UserResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path="/users")
public class UserController {

    private final UserService userService;
    private final UserMapper mapper;

    public UserController(UserService userService, UserMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @GetMapping()
    public ResponseEntity<Object> getAllUser(Pageable pageable) {
        Page<UserResource> resources = mapper.modelListToPage(userService.getAll(), pageable);
        return ApiController.ok(resources);
    }
}

package back.fruitback.userProfile.service;

import back.fruitback.userProfile.domain.model.User;
import back.fruitback.userProfile.domain.persistence.UserRepository;
import back.fruitback.userProfile.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

}

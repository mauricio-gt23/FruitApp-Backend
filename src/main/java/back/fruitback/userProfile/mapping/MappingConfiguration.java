package back.fruitback.userProfile.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("userProfileMappingConfiguration")
public class MappingConfiguration {

    @Bean
    public UserMapper userMapper() { return new UserMapper(); }

    @Bean
    public AdminMapper adminMapper() { return new AdminMapper(); }

    @Bean
    public ClientMapper clientMapper() { return new ClientMapper(); }
}

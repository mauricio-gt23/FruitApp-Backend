package back.fruitback.services.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("serviceMappingConfiguration")
public class MappingConfiguration {

    @Bean
    public ProductMapper productMapper() { return new ProductMapper(); }

    @Bean
    public FruitMapper fruitMapper() { return new FruitMapper(); }

    @Bean
    public JuiceMapper juiceMapper() { return new JuiceMapper(); }

    @Bean
    public SaleMapper saleMapper() { return new SaleMapper(); }

    @Bean
    public SaleDetailMapper saleDetailMapper() { return new SaleDetailMapper(); }
}

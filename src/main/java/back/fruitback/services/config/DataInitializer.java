package back.fruitback.services.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import back.fruitback.services.domain.model.Fruit;
import back.fruitback.services.domain.persistence.FruitRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private FruitRepository fruitRepository;

    @Override
    public void run(String... args) throws Exception {
        if (fruitRepository.count() == 0) {
            Fruit manzana = new Fruit(
                "Manzana",
                2.50f,
                "https://images.unsplash.com/photo-1560806887-1e4cd0b6cbd6?w=400"
            );

            Fruit platano = new Fruit(
                "Plátano",
                1.80f,
                "https://images.unsplash.com/photo-1571771894821-ce9b6c11b08e?w=400"
            );

            fruitRepository.save(manzana);
            fruitRepository.save(platano);

            System.out.println("✅ Frutas predeterminadas creadas exitosamente:");
            System.out.println("   - Manzana ($2.50)");
            System.out.println("   - Plátano ($1.80)");
        } else {
            System.out.println("ℹ️  Las frutas ya existen en la base de datos. No se crearon frutas predeterminadas.");
        }
    }
}
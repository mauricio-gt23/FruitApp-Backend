package back.fruitback.services.service;

import back.fruitback.services.domain.model.Fruit;
import back.fruitback.services.domain.model.Juice;
import back.fruitback.services.domain.persistence.FruitRepository;
import back.fruitback.services.domain.persistence.JuiceRepository;
import back.fruitback.services.domain.service.FruitService;
import back.fruitback.services.mapping.FruitMapper;
import back.fruitback.services.mapping.JuiceMapper;
import back.fruitback.services.resource.EditFruitRequest;
import back.fruitback.services.resource.FruitResource;
import back.fruitback.services.resource.JuiceResource;
import back.fruitback.services.resource.RegisterFruitRequest;
import back.fruitback.services.validator.EditFruitValidator;
import back.fruitback.services.validator.RegisterFruitValidator;
import back.fruitback.shared.application.Notification;
import back.fruitback.shared.application.Result;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FruitServiceImpl implements FruitService {

    private final FruitRepository fruitRepository;
    private final RegisterFruitValidator registerFruitValidator;
    private final EditFruitValidator editFruitValidator;
    private final FruitMapper mapper;

    public FruitServiceImpl(FruitRepository fruitRepository, RegisterFruitValidator registerFruitValidator, EditFruitValidator editFruitValidator, FruitMapper mapper) {
        this.fruitRepository = fruitRepository;
        this.registerFruitValidator = registerFruitValidator;
        this.editFruitValidator = editFruitValidator;
        this.mapper = mapper;
    }

    @Override
    public List<Fruit> getAll() {
        return fruitRepository.findAll();
    }

    @Override
    public FruitResource getById(Long fruitId) {
        return mapper.toResource(fruitRepository.getReferenceById(fruitId));
    }

    @Override
    public List<Fruit> getByName(String name) {
        return fruitRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public Result<FruitResource, Notification> register(RegisterFruitRequest request) {
        Notification notification = this.registerFruitValidator.validate(request);
        if (notification.hasErrors()) {
            return Result.failure(notification);
        }

        Fruit fruit = new Fruit(
                request.getName(),
                request.getPrice(),
                request.getUrlImage()
        );
        fruitRepository.save(fruit);

        FruitResource resource = mapper.toResource(fruit);
        return Result.success(resource);
    }

    @Override
    public Result<FruitResource, Notification> edit(Long fruitId, EditFruitRequest request) {
        Notification notification = this.editFruitValidator.validate(request);
        if (notification.hasErrors()) {
            return Result.failure(notification);
        }

        String name = fruitRepository.findById(fruitId).get().getName();
        String urlImage = fruitRepository.findById(fruitId).get().getUrlImage();

        fruitRepository.findById(fruitId).map(fruit -> {
            fruit.setPrice(request.getPrice());
            fruitRepository.save(fruit);
            return null;
        });

        Fruit fruit = new Fruit(
                fruitId,
                name,
                request.getPrice(),
                urlImage
        );

        FruitResource resource = mapper.toResource(fruit);
        return Result.success(resource);
    }
}

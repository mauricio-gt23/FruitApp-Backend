package back.fruitback.services.service;

import back.fruitback.services.domain.model.Juice;
import back.fruitback.services.domain.persistence.JuiceRepository;
import back.fruitback.services.domain.service.JuiceService;
import back.fruitback.services.mapping.JuiceMapper;
import back.fruitback.services.resource.EditJuiceRequest;
import back.fruitback.services.resource.JuiceResource;
import back.fruitback.services.resource.RegisterJuiceRequest;
import back.fruitback.services.validator.EditJuiceValidator;
import back.fruitback.services.validator.RegisterJuiceValidator;
import back.fruitback.shared.application.Notification;
import back.fruitback.shared.application.Result;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JuiceServiceImpl implements JuiceService {

    private final JuiceRepository juiceRepository;
    private final RegisterJuiceValidator registerJuiceValidator;
    private final EditJuiceValidator editJuiceValidator;
    private final JuiceMapper mapper;

    public JuiceServiceImpl(JuiceRepository juiceRepository, RegisterJuiceValidator registerJuiceValidator, EditJuiceValidator editJuiceValidator, JuiceMapper mapper) {
        this.juiceRepository = juiceRepository;
        this.registerJuiceValidator = registerJuiceValidator;
        this.editJuiceValidator = editJuiceValidator;
        this.mapper = mapper;
    }

    @Override
    public List<Juice> getAll() {
        return juiceRepository.findAll();
    }

    @Override
    public JuiceResource getById(Long juiceId) {
        return mapper.toResource(juiceRepository.getReferenceById(juiceId));
    }

    @Override
    public List<Juice> getByName(String name) {
        return juiceRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public Result<JuiceResource, Notification> register(RegisterJuiceRequest request) {
        Notification notification = this.registerJuiceValidator.validate(request);
        if (notification.hasErrors()) {
            return Result.failure(notification);
        }

        Juice juice = new Juice(
                request.getName(),
                request.getPrice(),
                request.getUrlImage()
        );
        juiceRepository.save(juice);

        JuiceResource resource = mapper.toResource(juice);
        return Result.success(resource);
    }

    @Override
    public Result<JuiceResource, Notification> edit(Long juiceId, EditJuiceRequest request) {
        Notification notification = this.editJuiceValidator.validate(request);
        if (notification.hasErrors()) {
            return Result.failure(notification);
        }

        String name = juiceRepository.findById(juiceId).get().getName();
        String urlImage = juiceRepository.findById(juiceId).get().getUrlImage();

        juiceRepository.findById(juiceId).map(juice -> {
            juice.setPrice(request.getPrice());
            juiceRepository.save(juice);
            return null;
        });

        Juice juice = new Juice(
                juiceId,
                name,
                request.getPrice(),
                urlImage
        );

        JuiceResource resource = mapper.toResource(juice);
        return Result.success(resource);
    }
}

package back.fruitback.services.service;

import back.fruitback.services.domain.model.Sale;
import back.fruitback.services.domain.model.enumerator.Status;
import back.fruitback.services.domain.persistence.SaleRepository;
import back.fruitback.services.domain.service.SaleService;
import back.fruitback.services.mapping.SaleMapper;
import back.fruitback.services.resource.EditSaleRequest;
import back.fruitback.services.resource.RegisterSaleRequest;
import back.fruitback.services.resource.SaleResource;
import back.fruitback.services.validator.EditSaleValidator;
import back.fruitback.services.validator.RegisterSaleValidator;
import back.fruitback.shared.application.Notification;
import back.fruitback.shared.application.Result;
import back.fruitback.userProfile.domain.model.User;
import back.fruitback.userProfile.domain.persistence.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;
    private final UserRepository userRepository;
    private final RegisterSaleValidator registerSaleValidator;
    private final EditSaleValidator editSaleValidator;
    private final SaleMapper saleMapper;

    public SaleServiceImpl(SaleRepository saleRepository, UserRepository userRepository, RegisterSaleValidator registerSaleValidator, EditSaleValidator editSaleValidator, SaleMapper saleMapper) {
        this.saleRepository = saleRepository;
        this.userRepository = userRepository;
        this.registerSaleValidator = registerSaleValidator;
        this.editSaleValidator = editSaleValidator;
        this.saleMapper = saleMapper;
    }

    @Override
    public List<Sale> getAll() {
        return saleRepository.findAll();
    }

    @Override
    public SaleResource getById(Long saleId) {
        return saleMapper.toResource(saleRepository.getReferenceById(saleId));
    }

    @Override
    public List<Sale> getByUserId(Long userId) {
        return saleRepository.findByUserId(userId);
    }

    @Override
    public Result<SaleResource, Notification> register(RegisterSaleRequest request) {
        Notification notification = this.registerSaleValidator.validate(request);
        if (notification.hasErrors()) {
            return Result.failure(notification);
        }

        User user = userRepository.getReferenceById(request.getUserId());

        Sale sale = new Sale(
                user,
                request.getTotalPrice(),
                Status.PENDING
        );
        saleRepository.save(sale);

        SaleResource resource = saleMapper.toResource(sale);
        return Result.success(resource);
    }

    @Override
    public Result<SaleResource, Notification> edit(Long saleId, EditSaleRequest request) {
        Notification notification = this.editSaleValidator.validate(request);
        if (notification.hasErrors()) {
            return Result.failure(notification);
        }

        saleRepository.findById(saleId).map(sale -> {
            sale.setStatus(Status.valueOf(request.getProductType()));
            saleRepository.save(sale);
            return null;
        });

        Sale sale = saleRepository.getReferenceById(saleId);

        SaleResource resource = saleMapper.toResource(sale);
        return Result.success(resource);
    }
}

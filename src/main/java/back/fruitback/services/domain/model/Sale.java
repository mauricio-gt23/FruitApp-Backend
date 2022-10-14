package back.fruitback.services.domain.model;

import back.fruitback.services.domain.model.enumerator.Status;
import back.fruitback.shared.domain.model.AuditModel;
import back.fruitback.userProfile.domain.model.Client;
import back.fruitback.userProfile.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "sales")
public class Sale extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name ="user_id", nullable = false)
    private User user;

    @OneToMany(
            mappedBy = "sale",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<SaleDetail> products = new ArrayList<>();

    private Double totalPrice;

    @Enumerated(EnumType.STRING)
    private Status status;

    public Sale(User user, Double totalPrice, Status status) {
        this.user = user;
        this.totalPrice = totalPrice;
        this.status = status;
    }


}

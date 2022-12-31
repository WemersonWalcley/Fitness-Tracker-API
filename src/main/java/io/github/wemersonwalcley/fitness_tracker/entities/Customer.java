package io.github.wemersonwalcley.fitness_tracker.entities;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_customer")
public class Customer{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String listaTreino;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "tb_account_id")
    @MapsId
    private Account account;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "tb_credential_FK")
    private Credential credential;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Customer customer = (Customer) o;
        return getId() != null && Objects.equals(getId(), customer.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

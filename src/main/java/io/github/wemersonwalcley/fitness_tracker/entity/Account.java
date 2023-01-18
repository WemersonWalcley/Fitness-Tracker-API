package io.github.wemersonwalcley.fitness_tracker.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.github.wemersonwalcley.fitness_tracker.enumeration.AccessLevelEnum;
import lombok.*;
import org.hibernate.Hibernate;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@ToString
@Table(name="tb_account")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Account implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotEmpty(message = "Campo nome é obrigatório.")
    private String name;

    @Column
    @NotEmpty(message = "Campo e-mail é obrigatório.")
    private String email;

    @Column
    private LocalDate birthday;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Credential credential;

    @Enumerated(EnumType.STRING)
    @JsonBackReference
    private AccessLevelEnum accessLevelEnum;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Account account = (Account) o;
        return getId() != null && Objects.equals(getId(), account.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

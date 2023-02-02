package io.github.wemersonwalcley.fitness_tracker.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@Table(name="tb_account")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class AccountModel implements Serializable {

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
    private CredentialModel credentialModel;

}

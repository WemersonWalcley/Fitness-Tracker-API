package io.github.wemersonwalcley.fitness_tracker.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.github.wemersonwalcley.fitness_tracker.enumeration.AccessLevelEnum;
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

}

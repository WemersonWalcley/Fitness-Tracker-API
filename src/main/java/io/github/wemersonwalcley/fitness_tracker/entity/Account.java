package io.github.wemersonwalcley.fitness_tracker.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.github.wemersonwalcley.fitness_tracker.enumeration.AccessLevelEnum;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;

@Getter
@Setter
@ToString
@Table(name="tb_account")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Account implements Serializable, GrantedAuthority, UserDetails {

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
    public String getAuthority() {
        return this.accessLevelEnum.name();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}

package io.github.wemersonwalcley.fitness_tracker.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.github.wemersonwalcley.fitness_tracker.enumeration.AccessLevelEnum;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Objects;

@Getter
@Setter
@ToString
@Table(name="tb_account")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
@Builder
public class Account implements UserDetails, GrantedAuthority, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @NotEmpty(message = "Campo nome é obrigatório.")
    private String name;

    @Column
    @NotEmpty(message = "Campo e-mail é obrigatório.")
    private String email;

    @Column(length = 200)
    @NotEmpty(message = "Campo senha é obrigatório.")
    private String password;

    @Column
    private LocalDate birthday;

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


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        switch (accessLevelEnum) {
            case ADMIN:
                return accessLevelEnum.name();
            default:
                return "default";
        }
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

    @Override
    public String getAuthority() {
        switch (accessLevelEnum) {
            case ADMIN:
                return "ROLE_ADMIN";
            default:
                return "ROLE_DEFAULT";
        }
    }
}

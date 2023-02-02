package io.github.wemersonwalcley.fitness_tracker.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.github.wemersonwalcley.fitness_tracker.enumeration.AccessLevelEnum;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CredentialModel implements Serializable, GrantedAuthority, UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;

    @Column(length = 200)
    @NotEmpty(message = "Campo senha é obrigatório.")
    private String password;

    @Enumerated(EnumType.STRING)
    @JsonBackReference
    private AccessLevelEnum accessLevelEnum;

    @Override
    public String getAuthority() {
        switch (accessLevelEnum) {
            case ADMIN:
                return "ROLE_ADMIN";
            case USER:
                return "ROLE_USER";
            default:
                return "ROLE_DEFAULT";
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(accessLevelEnum.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

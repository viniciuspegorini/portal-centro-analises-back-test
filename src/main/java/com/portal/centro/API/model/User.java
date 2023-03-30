package com.portal.centro.API.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.portal.centro.API.user.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated
    private Type role;

    @NotNull(message = "Parameter name is required.")
    @Size(min = 4, max = 255)
    private String name;

    @NotNull(message = "Parameter username is required.")
    @Size(min = 4, max = 255)
    private String username;

    @NotNull(message = "Parameter password is required.")
    @Size(min = 6, max = 254)
//    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$")
    private String password;

    @NotNull(message = "Parameter email is required.")
    private String email;

    @Column(nullable = false)
    private LocalDateTime inclusionDate;


    @Override
    @Transient
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<>();
        list.addAll(this.authorities);
        return list;
    }

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Getter
    @JoinTable(name = "user_authorities",
            joinColumns = @JoinColumn(
                    name = "tb_user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "authority_id", referencedColumnName = "id"))
    private Set<Permission> authorities;

    @Override
    @Transient
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @Transient
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @Transient
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @Transient
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }

}

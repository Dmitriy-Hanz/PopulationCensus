package populationcensus.repository.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access=AccessLevel.PRIVATE, force=true)
@Getter

@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;


    @Column(nullable = false)
    private final String username;

    @Column(nullable = false)
    private final String password;


    @Column(name = "p_name")
    private String name;

    @Column(name = "p_surname")
    private String surname;

    @Column(name = "p_fathername")
    private String fathername;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
//        SimpleGrantedAuthority role_user;
//        if (ADMIN_USERNAME.equals(username)) {
//            role_user = new SimpleGrantedAuthority("ROLE_ADMIN");
//        } else {
//            role_user = new SimpleGrantedAuthority("ROLE_USER");
//        }
//        return List.of(role_user);
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

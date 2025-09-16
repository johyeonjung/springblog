package me.johyeonjung.myspringblog.security;

import lombok.Getter;
import me.johyeonjung.myspringblog.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
public class UserPrincipal implements UserDetails {

    private final Long id;
    private final String username;
    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;

    private final boolean accountNonExpired;
    private final boolean accountNonLocked;
    private final boolean credentialsNonExpired;
    private final boolean enabled;

    public UserPrincipal(Long id,
                         String username,
                         String password,
                         Collection<? extends GrantedAuthority> authorities,
                         boolean accountNonExpired,
                         boolean accountNonLocked,
                         boolean credentialsNonExpired,
                         boolean enabled) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities=authorities;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.enabled = enabled;
    }

    public static UserPrincipal create(User u) {
        return new UserPrincipal(
                u.getId(),
                u.getUsername(),
                u.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_USER")),
                true,true,true,true

        );
    }

    @Override public String getUsername() { return username; }
    @Override public String getPassword() { return password; }
    @Override public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities; }

    @Override public boolean isAccountNonExpired() { return this.accountNonExpired; }
    @Override public boolean isAccountNonLocked() { return this.accountNonLocked; }
    @Override public boolean isCredentialsNonExpired() { return this.credentialsNonExpired; }
    @Override public boolean isEnabled() { return this.enabled; }
}

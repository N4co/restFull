package br.com.felix.projeto.model;

import jakarta.persistence.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User implements UserDetails, Serializable {

    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "user_name", unique = true)
    private String userName;
    @Column(name = "full_name")
    private String fullname;
    @Column(name = "password")
    private String password;
    @Column(name = "account_non_expired")
    private Boolean accountNonExpíred;
    @Column(name = "account_non_locked")
    private Boolean accountNonLocked;
    @Column(name = "enabled")
    private Boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_permission", joinColumns = {@JoinColumn(name = "id_user")},
    inverseJoinColumns = {@JoinColumn (name = "id_permission")})
    private static List<Permission> permissions;

    public User() {
    }


    public static List <String> getRoles() {
        List<String> roles = new ArrayList<>();
        for (Permission permission : permissions) {
            roles.add(permission.getDescription());
            
        }
        return roles;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
      return this.permissions;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpíred;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return this.isEnabled();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getAccountNonExpíred() {
        return accountNonExpíred;
    }

    public void setAccountNonExpíred(Boolean accountNonExpíred) {
        this.accountNonExpíred = accountNonExpíred;
    }

    public Boolean getAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(Boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User that)) return false;
        return id == that.id && Objects.equals(userName, that.userName) && Objects.equals(fullname, that.fullname) && Objects.equals(password, that.password) && Objects.equals(accountNonExpíred, that.accountNonExpíred) && Objects.equals(accountNonLocked, that.accountNonLocked) && Objects.equals(enabled, that.enabled) && Objects.equals(permissions, that.permissions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, fullname, password, accountNonExpíred, accountNonLocked, enabled, permissions);
    }
}

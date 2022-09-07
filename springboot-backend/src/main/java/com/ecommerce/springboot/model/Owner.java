package com.ecommerce.springboot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Owner implements UserDetails
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ownerId;

    private String ownerName;
    private String email;
    private String password;

    @OneToMany(mappedBy = "owner",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Product> productList=new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name="owner_role",
            joinColumns=@JoinColumn(name="owner",referencedColumnName = "ownerId"),inverseJoinColumns = @JoinColumn(name="roleName",referencedColumnName = "roleId"))
    private Set<Role>roles= new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<SimpleGrantedAuthority> authorities=this.roles.stream().map((role) ->new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());

        return authorities;
    }

    @Override
    public String getUsername() {
        return this.email;
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

//    @ManyToMany
//    @JoinTable(name="owner_role", joinColumns=@JoinColumn(name="owner",referencedColumnName = "ownerId"),inverseJoinColumns = @JoinColumn(name="role",referencedColumnName = "roleId"))
//    private Set<Role> roles=new HashSet<>();
}

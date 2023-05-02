package com.sysmap.parrot.domain.entities;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Data
public class User implements UserDetails {
    private UUID id;
    private String name;
    private String email;
    private String password;
    private String pictureUrl;

    public User (String name, String email, String password, String pictureUrl) {
        this.setId();
        this.name = name;
        this.email = email;
        this.password = password;
        this.pictureUrl = pictureUrl;
    }

    protected void setId() {
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return this.id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return email;
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



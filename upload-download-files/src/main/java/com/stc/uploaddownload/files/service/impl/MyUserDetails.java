package com.stc.uploaddownload.files.service.impl;

import com.stc.uploaddownload.files.domain.DBPermissionGroups;
import com.stc.uploaddownload.files.domain.DBPermissions;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
public class MyUserDetails implements UserDetails {

    private DBPermissions user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<DBPermissionGroups> roles = user.getRoleGroup();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (DBPermissionGroups role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getGroupName()));
        }

        return authorities;
    }

    @Override
    public String getPassword()
    {
        return user.getPassword();
    }

    @Override
    public String getUsername()
    {
        return user.getUserEmail();
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    public boolean isEnabled()
    {
        return true;
    }
}

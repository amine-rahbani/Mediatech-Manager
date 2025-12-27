package com.estc.mediatech.service;

import com.estc.mediatech.models.UserEntity;
import com.estc.mediatech.Repositories.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1. Fetch user from DB (throws error if not found)
        UserEntity user = userDao.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        // 2. Build the Security User
        // The .roles() method automatically adds "ROLE_" to the value.
        // So "ADMIN" becomes "ROLE_ADMIN".
        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole()) 
                .build();
    }
}
package com.jencf.codefellowship.configs;

import com.jencf.codefellowship.models.ApplicationUser;
import com.jencf.codefellowship.repositories.ApplicationUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ApplicationUserRepo applicationUserRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ApplicationUser user = applicationUserRepo.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Error: username incorrect or not found");
        }
        return user;
    }
}


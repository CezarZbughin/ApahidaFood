package com.cezar.apahida.configuration;

import com.cezar.apahida.model.EndUser;
import com.cezar.apahida.repository.EndUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private EndUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<EndUser> user = userRepository.findByUsername(username);
        if(user.isEmpty()){
            throw new UsernameNotFoundException(username);
        }
        return user.get();
    }
}
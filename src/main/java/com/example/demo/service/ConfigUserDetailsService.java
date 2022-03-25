package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class ConfigUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public ConfigUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public static User initUser(User user) {
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(ERole -> new SimpleGrantedAuthority(ERole.name()))
                .collect(Collectors.toList());

        return new User(user.getId(), user.getUsername(), user.getPassword(), user.getEmail(), authorities);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found:" + username));
        return initUser(user);
    }

    public User loadUserByUserId(Long userId) {

        return null;
    }
}

    
    







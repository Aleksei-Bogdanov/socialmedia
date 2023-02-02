package com.socialmedia.services;

import com.socialmedia.domain.Role;
import com.socialmedia.domain.User;
import com.socialmedia.repos.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserService implements UserDetailsService {
    private final UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }

    public void saveUser(
            String username,
            Map<String,String> form,
            User user
    ){
        user.setUsername(username);

        user.getRoles().clear();
        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        for (String key: form.keySet()){
            if (roles.contains(key)){
                user.getRoles().add(Role.valueOf(key));
            }
        }
        userRepo.save(user);
    }

    public List<User> findAllUsers(){
        return userRepo.findAll();
    }
}

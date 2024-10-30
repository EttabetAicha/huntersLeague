package org.aicha.hunters_leagues.service;

import org.aicha.hunters_leagues.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }



}

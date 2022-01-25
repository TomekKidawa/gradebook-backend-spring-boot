//my could be deleted later

package com.example.gradebook.security.services;

import com.example.gradebook.models.User;
import com.example.gradebook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import  java.util.List;

@Service
public class UserService {
    private final UserRepository userRepo;

    @Autowired
    public UserService(UserRepository userRepo){
        this.userRepo = userRepo;
    }

//    public List<User>findAllUsers(){
//        return userRepo.findAll();
//    }

    public void deleteUserById(Long id){
        userRepo.deleteById(id);
    }

    public User findUserById(Long id){
        return userRepo.findById(id).get();
    }

    public User updateUser(User user){
        return userRepo.save(user);
    }

    public Page<User> findAllUsers(Pageable paging){
        return userRepo.findAll(paging);
    }

    public Page<User>findAllUsersByUsername(String username, Pageable pageable){
        return userRepo.findByUsernameContaining(username, pageable);
    }

}

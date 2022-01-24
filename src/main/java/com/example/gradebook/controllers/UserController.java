//my could be deleted later, just testing

package com.example.gradebook.controllers;

import com.example.gradebook.models.User;
import com.example.gradebook.payload.response.MessageResponse;
import com.example.gradebook.repository.UserRepository;
import com.example.gradebook.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @Autowired
    UserRepository userRepository;

    @GetMapping("/all")
    public ResponseEntity<List<User>>getAllUsers(){
        List<User> user = userService.findAllUsers();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public  ResponseEntity<User>getUserById(@PathVariable("id") Long id ){
        User user = userService.findUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id ){
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    ////Works but not
//
//    @PostMapping("/edit/{id}")
//    public ResponseEntity<User>updateUser(@RequestBody User user){
//        User updateUser = userService.updateUser(user);
//    return  new ResponseEntity<>(updateUser, HttpStatus.OK);
//    }

    //dodac pre authorize
    @PostMapping("/edit/{id}")
    public ResponseEntity<User>updateUser(@PathVariable("id") Long id,@RequestBody User userDetails){
        User updateUser = userService.findUserById(id);

        updateUser.setUsername(userDetails.getUsername());
        updateUser.setEmail(userDetails.getEmail());
        updateUser.setPassword(userDetails.getPassword());
        updateUser.setRoles(userDetails.getRoles());
        userService.updateUser(updateUser);
        return ResponseEntity.ok(updateUser);
    }



    //test api
    @GetMapping("/spr")
    public String spr(){
        return "spr dziala";
    }


}

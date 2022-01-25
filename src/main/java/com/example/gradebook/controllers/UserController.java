//my could be deleted later, just testing

package com.example.gradebook.controllers;

import com.example.gradebook.models.User;
import com.example.gradebook.payload.response.MessageResponse;
import com.example.gradebook.repository.UserRepository;
import com.example.gradebook.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<Map<String, Object>> getAllUsers(@RequestParam(required = false)String username,
                                                           @RequestParam(defaultValue = "0")int page,
                                                           @RequestParam (defaultValue = "3") int size){
        List<User> users = new ArrayList<User>();
        Pageable paging = PageRequest.of(page, size);
        Page<User> pageUsers;
        if(username == null){
            pageUsers = userService.findAllUsers(paging);
        }else{
            pageUsers = userService.findAllUsersByUsername(username, paging);
        }

        users = pageUsers.getContent();

        Map<String, Object> response = new HashMap<>();
        response.put("users", users);
        response.put("currentPage", pageUsers.getNumber());
        response.put("totalItems", pageUsers.getTotalElements());
        response.put("totalPages", pageUsers.getTotalPages());

        return new ResponseEntity<>(response, HttpStatus.OK);

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

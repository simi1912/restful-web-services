package com.simi.rest.webservices.restfulwebservices.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserResource {

    private UserDAOService userService;

    public UserResource(UserDAOService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> retriveAllUsers(){
        return  userService.findAll();
    }

    @GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable Integer id){
        return userService.findOne(id);
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping(value = "/users")
    public ResponseEntity<Object> createUsers(@RequestBody User user){
        User savedUser = userService.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

}

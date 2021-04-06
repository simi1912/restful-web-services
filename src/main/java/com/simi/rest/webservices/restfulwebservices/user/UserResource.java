package com.simi.rest.webservices.restfulwebservices.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    public User createUsers(@RequestBody User user){
        return userService.save(user);
    }

}

package com.simi.rest.webservices.restfulwebservices.user;

import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
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
    public EntityModel<User> retrieveUser(@PathVariable Integer id){
        User user = userService.findOne(id);

        if(user == null){
            throw  new UserNotFoundException("id-"+id);
        }

        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retriveAllUsers());

        EntityModel<User> resource = EntityModel.of(user);
        resource.add(linkTo.withRel("all-users"));

        return resource;
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping(value = "/users")
    public ResponseEntity<Object> createUsers(@Valid @RequestBody User user){
        User savedUser = userService.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Integer id){
        User user = userService.deleteById(id);

        if(user == null){
            throw  new UserNotFoundException("id-"+id);
        }
    }

}

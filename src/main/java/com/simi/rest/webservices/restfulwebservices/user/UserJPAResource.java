package com.simi.rest.webservices.restfulwebservices.user;


import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserJPAResource {

    private UserRepository userRepository;

    public UserJPAResource(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/jpa/users")
    public List<User> retrieveAllUsers(){
        return  userRepository.findAll();
    }

    @GetMapping("/jpa/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable Integer id){
        Optional<User> user = userRepository.findById(id);

        if(!user.isPresent()){
            throw  new UserNotFoundException("id-"+id);
        }

        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());

        EntityModel<User> resource = EntityModel.of(user.get());
        resource.add(linkTo.withRel("all-users"));

        return resource;
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping(value = "/jpa/users")
    public ResponseEntity<Object> createUsers(@Valid @RequestBody User user){
        User savedUser = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/jpa/{id}")
                .buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/jpa/users/{id}")
    public void deleteUser(@PathVariable Integer id){
        userRepository.deleteById(id);

    }


}

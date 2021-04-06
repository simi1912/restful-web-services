package com.simi.rest.webservices.restfulwebservices.helloworld;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping(value = "/hello-world")
    public String helloWorld(){
        return  "Hello world";
    }

    @GetMapping(value = "/hello-world-bean", produces = MediaType.APPLICATION_XML_VALUE)
    public HelloWorldBean helloWorldBean(){
        return new HelloWorldBean("Hello world");
    }

    @GetMapping(value = "/path-variable/{name}", produces = MediaType.APPLICATION_XML_VALUE)
    public HelloWorldBean helloWorldPathVariable(@PathVariable String name){
        return new HelloWorldBean(String.format("Hello world, %s", name));
    }

}

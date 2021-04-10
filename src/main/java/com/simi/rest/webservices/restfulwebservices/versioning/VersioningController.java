package com.simi.rest.webservices.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningController {

    @GetMapping("v1/person")
    public PersonV1 personV1(){
        return new PersonV1("Ana Doroftei");
    }

    @GetMapping("v2/person")
    public PersonV2 personV2(){
        return new PersonV2(new Name("Ioan", "Simiciuc"));
    }

    @GetMapping(value = "/person/param", params = "version=1")
    public PersonV1 personParamV1(){
        return new PersonV1("Ana Doroftei");
    }

    @GetMapping(value = "/person/param", params = "version=2")
    public PersonV2 personParamV2(){
        return new PersonV2(new Name("Ioan", "Simiciuc"));
    }

    @GetMapping(value = "/person/header", headers = "X-API-VERSION=1")
    public PersonV1 personHeaderV1(){
        return new PersonV1("Ana Doroftei");
    }

    @GetMapping(value = "/person/header", headers = "X-API-VERSION=2")
    public PersonV2 personHeaderV2(){
        return new PersonV2(new Name("Ioan", "Simiciuc"));
    }

}

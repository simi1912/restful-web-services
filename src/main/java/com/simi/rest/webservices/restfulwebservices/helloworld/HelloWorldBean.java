package com.simi.rest.webservices.restfulwebservices.helloworld;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class HelloWorldBean {

    private String message;

    public HelloWorldBean() {
    }

    public HelloWorldBean(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

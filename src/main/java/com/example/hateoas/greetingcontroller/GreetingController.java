package com.example.hateoas.greetingcontroller;

import com.example.hateoas.domain.Greet;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class GreetingController {

    @RequestMapping("/greeting")
    @ResponseBody
    public HttpEntity<Greet> greeting(@RequestParam(value = "name",required = false,defaultValue = "hateoas") String name){
        Greet greet = new Greet("Hello " + name);
        /*The below is used for HATEOAS, HATEOAS is an RESTful pattern that provides relationship
        between UI and backend. Here we pass link as a part of payload metadata.The client application
        determines the state and follows the transition URLs provided by payload.

        linkTo(methodOn(GreetingController.class).greeting(name)).withSelfRel(), this adds self-contained
        web link to Greet object.

        To do this the Greet class must extend ResourceSupport class

        * */
        greet.add(linkTo(methodOn(GreetingController.class).greeting(name)).withSelfRel());
        return new ResponseEntity<>(greet,HttpStatus.OK);
    }
}
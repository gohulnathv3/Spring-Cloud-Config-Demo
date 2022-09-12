package io.spring.springbootconfig;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class GreetingController {


    //Injecting value from property file
    @Value("${my.greetings: default value}") // Trick 1 for value if our file not present in prop file, we can use the default value
    // Declaring member variable to restore the value from property file
    private String greetingMessage;

    @Value("${my.message}")
    private String message;

    @Value("some static message") // We can directly add the strings externally using @value
    private String staticMessage;

    @Value("${My.list.values}") // We can use @value to show list of values that we've declared in the prop file
    private List<Integer> myList;

    // Declaring key value pairs in prop file
    @Value("#{${DbValues}}")
    private Map<String, String> dbValues;

    @Autowired
    private DbSettings dbSettings; // get all those values in one shot.

    @Value("Welcome to @Value static message")
    private String Hello;


   @GetMapping("/greeting")
    public String greeting(){
//        return "Hello"; // Usual declaration
       return greetingMessage+ message + myList + dbSettings.getConnection() + dbSettings.getHost(); // Returning values from property file

    }

    @GetMapping("/hello")
    public String hello(){
       return Hello;
    }
}

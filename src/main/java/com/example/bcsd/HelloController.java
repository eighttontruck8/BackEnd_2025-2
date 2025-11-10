package com.example.bcsd;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello"; //templates/hello.html 을 반환
    }
}

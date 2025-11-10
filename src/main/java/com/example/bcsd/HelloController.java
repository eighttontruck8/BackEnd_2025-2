package com.example.bcsd;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    //  @ResponseBody
    @GetMapping("/hello")
    public String hello() {
//        return "Hello World"; //Hello World 라는 문자열을 반환
        return "hello"; //templates/hello.html 을 반환
    }
}

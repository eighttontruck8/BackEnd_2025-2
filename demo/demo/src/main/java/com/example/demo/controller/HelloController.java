// controller는 어떤 로직을 호출할건지 요청을 받는다.
// 즉, controller -> service

package com.example.demo.controller;
import com.example.demo.service.HelloService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/introduce")
public class HelloController {

    private final HelloService helloService;
    public HelloController(HelloService helloService){
        this.helloService = helloService;
    }

    // 1) /introduce/html -> introduce.html 반환
    @GetMapping("/html")
    public String hello(Model model) {
        return "introduce";
    }

    // 2) /introduce/string?name=이름 -> "안녕하세요. 제 이름은 000 입니다."
    @GetMapping("/string")
    public String introduceString(@RequestParam String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    // 3) /json에 json형태로 반환
    @GetMapping("/json")
    @ResponseBody
    public Map<String, Object> introduceJson() {
        return helloService.getHelloJson();
    }
}

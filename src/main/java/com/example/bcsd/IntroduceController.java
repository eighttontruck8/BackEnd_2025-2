package com.example.bcsd;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@Controller
public class IntroduceController {
    @GetMapping("/introduce/html")
    public String introduce() {
        return "introduce";
    }
    // @GetMapping은 /greeting에 대한 HTTP GET 요청이 greeting() 메서드에 매핑되도록 합니다.
    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }
}

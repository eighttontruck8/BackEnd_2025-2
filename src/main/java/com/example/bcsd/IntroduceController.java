package com.example.bcsd;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Controller
public class IntroduceController {
    @GetMapping("/introduce/html")
    public String introduce() {
        return "introduce";
    }

    // @GetMapping은 /introduce/에 대한 HTTP GET 요청이 introduce() 메서드에 매핑되도록 합니다.
    @GetMapping("/introduce/string") // 쿼리는 여기에 미포함
    public String introduce2(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "hello " + name + "!";
    }
}

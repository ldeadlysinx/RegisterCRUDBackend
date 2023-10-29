package com.register.registertest.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*" )
public class TestController {
    @GetMapping("/boards")
    public String test(){
        return "index";
    }

    @RequestMapping("/test/testdata")
    public String hello() {
        return "안녕하세요 리액트와 스프링부트를 Proxy 설정을통해연결하고 있습니다";
    }
}

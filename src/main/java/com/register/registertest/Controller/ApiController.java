package com.register.registertest.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiController {

    @GetMapping("/data")
    public ResponseEntity<Map<String, String>> getData() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Hello from Spring Boot!"); // 응답 데이터 설정
        return ResponseEntity.ok(response);
    }
}
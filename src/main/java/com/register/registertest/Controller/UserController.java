package com.register.registertest.Controller;

import com.register.registertest.config.auth.PrincipalDetails;
import com.register.registertest.dto.RegisterDto;
import com.register.registertest.entity.User;
import com.register.registertest.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/test/auth/register")
    public User register(@RequestBody RegisterDto registerDto) {
        System.out.println(registerDto);
        userService.register(registerDto);
        return null;
    }

    @GetMapping("/Get/Username")
    public String getUserName(@AuthenticationPrincipal PrincipalDetails principalDetails){
        String username;
        username=userService.getUsername(principalDetails.getUser());
        return username;
    }
}

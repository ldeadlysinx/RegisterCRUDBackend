package com.register.registertest.OAuthController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.register.registertest.config.auth.PrincipalDetails;
import com.register.registertest.config.jwt.JwtProperties;
import com.register.registertest.dto.RegisterDto;
import com.register.registertest.entity.User;
import com.register.registertest.repository.UserRepository;
import com.register.registertest.service.LoginService;
import com.register.registertest.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@RestController
@RequestMapping(value = "/check/oauth2", produces = "application/json")
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    final LoginService loginService;
    final UserRepository userRepository;

    @Autowired
    final UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;


    @GetMapping("/code/{registrationId}")
    public void googleLogin(@RequestParam String code, @PathVariable String registrationId, HttpServletResponse response) {
        RegisterDto googleuser = loginService.socialLogin(code, registrationId);
        System.out.println("code"+code);
        User checkuser = userRepository.findByUsername(googleuser.getUsername());

        if(checkuser==null){
            System.out.println("기존의 회원 아님");
            userService.register(googleuser);
            checkuser.setPassword(googleuser.getPassword());
        }

        System.out.println("자동로그인");
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(googleuser.getUsername(), googleuser.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();

        String jwtToken = JWT.create()
                .withSubject(principalDetails.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis()+ JwtProperties.EXPIRATION_TIME))
                .withClaim("id", principalDetails.getUser().getId())
                .withClaim("username", principalDetails.getUser().getUsername())
                .sign(Algorithm.HMAC512(JwtProperties.SECRET));

        System.out.println(jwtToken);
        response.addHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX+jwtToken);
    }
}

package io.getarrays.userservice.Controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.getarrays.userservice.entity.Role;
import io.getarrays.userservice.entity.User;
import io.getarrays.userservice.service.UserService;
import io.getarrays.userservice.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequiredArgsConstructor

public class Token {

    private final io.getarrays.userservice.service.UserService userService;
//    @GetMapping("/token/refresh")
//    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        String authorizationHeader = request.getHeader(AUTHORIZATION);
//        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
//            try {
//                String refresh_token = authorizationHeader.substring("Bearer ".length());
//                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
//                JWTVerifier verifier = JWT.require(algorithm).build();
//                DecodedJWT decodedJWT = verifier.verify(refresh_token);
//                String username = decodedJWT.getSubject();
//                User user = userService.getUser(username);
//                String access_token = JWT.create()
//                        .withSubject(user.getUsername())
//                        .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
//                        .withIssuer(request.getRequestURI().toString())
//                        .withClaim("roles", user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
//                        .sign(algorithm);
//                Map<String, String> tokens = new HashMap<>();
//                tokens.put("access_token", access_token);
//                tokens.put("refresh_token", refresh_token);
//                response.setContentType(APPLICATION_JSON_VALUE);
//                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
//            } catch (Exception exception) {
//                response.setHeader("error", exception.getMessage());
//                response.setStatus(FORBIDDEN.value());
//
//                Map<String, String> error = new HashMap<>();
//                error.put("error_message", exception.getMessage());
//                response.setContentType(APPLICATION_JSON_VALUE);
//                new ObjectMapper().writeValue(response.getOutputStream(), error);
//            }
//
//        } else {
//            throw new RuntimeException("Refresh token is missing");
//        }
//    }

    @GetMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader=request.getHeader(AUTHORIZATION);

        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer")){
            try{
                String refresh_token =authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm=Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier= JWT.require(algorithm).build();
                DecodedJWT decodedJWT=verifier.verify(refresh_token);
                String username =decodedJWT.getSubject();
                User user=userService.getUser(username);

                String access_token= JWT.create()
                        .withSubject(user.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis()+10*60*1000))
                        .withIssuer(request.getRequestURI().toString())
                        .withClaim("roles",user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                        .sign(algorithm);


                Map<String,String> tokens=new HashMap<>();
                tokens.put("access_token",access_token);
                tokens.put("refresh_token",refresh_token);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(),tokens);
            }
            catch (Exception ex){
                response.setHeader("error",ex.getMessage());
                 response.setStatus(FORBIDDEN.value());

                Map<String,String> error=new HashMap<>();
                error.put("error_message",ex.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(),error);


            }
        }else{
            throw new RuntimeException("Refresh toekn is missing");
        }
    }
}

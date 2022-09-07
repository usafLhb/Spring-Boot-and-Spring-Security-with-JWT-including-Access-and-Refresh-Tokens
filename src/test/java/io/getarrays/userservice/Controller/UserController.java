package io.getarrays.userservice.Controller;

import io.getarrays.userservice.domaine.User;
import io.getarrays.userservice.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImpl UserService;

    @GetMapping()
    public ResponseEntity<List<User>>getUser(){
        return ResponseEntity.ok().body(UserService.getUsers());
    }

}

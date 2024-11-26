package org.personsal.mybatis.controller.user;

import lombok.RequiredArgsConstructor;
import org.personsal.mybatis.entity.User;
import org.personsal.mybatis.service.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<String> insertUsers() {
        return ResponseEntity.ok(this.userService.insertUser());
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok(this.userService.getAllUsers());
    }

    @GetMapping("{id}")
    public ResponseEntity<User> findUserById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.userService.getUserById(id));
    }
}

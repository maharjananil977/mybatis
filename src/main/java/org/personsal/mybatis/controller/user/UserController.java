package org.personsal.mybatis.controller.user;

import lombok.RequiredArgsConstructor;
import org.personsal.mybatis.common.page.CustomPage;
import org.personsal.mybatis.common.page.SearchRequest;
import org.personsal.mybatis.common.response.BaseResponse;
import org.personsal.mybatis.domain.user.UserSearchRequest;
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

    @PostMapping
    public ResponseEntity<BaseResponse<CustomPage<User>>> findAll(@RequestBody UserSearchRequest request) {
        return ResponseEntity.ok(this.userService.getAllUsers(request));
    }

    @GetMapping("{id}")
    public ResponseEntity<BaseResponse<User>> findUserById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.userService.getUserById(id));
    }
}

package com.megamaker.codechallenge.controller;

import com.megamaker.codechallenge.dto.user.RequestUserEdit;
import com.megamaker.codechallenge.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {
    private final UserService userService;


    @PutMapping
    public ResponseEntity edit(@RequestBody RequestUserEdit requestUserEdit) {
        userService.edit(requestUserEdit);
        return ResponseEntity.noContent().build();
    }
}

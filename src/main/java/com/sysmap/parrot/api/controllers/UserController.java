package com.sysmap.parrot.api.controllers;

import com.sysmap.parrot.application.requests.Security.IJwtService;
import com.sysmap.parrot.application.requests.User.CreateUser.CreateUserRequest;
import com.sysmap.parrot.application.requests.User.GetUser.GetUserResponse;
import com.sysmap.parrot.application.requests.User.IUserService;
import com.sysmap.parrot.application.requests.User.UpdateUser.UpdateUserRequest;
import com.sysmap.parrot.application.requests.User.UpdateUser.UpdateUserResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private IUserService _userService;

    @Autowired
    private IJwtService _jwtService;

    @PostMapping()
    public ResponseEntity<String> createUser(@RequestBody @Valid CreateUserRequest request) {
        var response = _userService.createUser(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetUserResponse> getUser(@PathVariable String id) {
        var response = _userService.getUserById(id);

        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateUserResponse> updateUser(@PathVariable String id, @RequestBody UpdateUserRequest request) {
       var response = _userService.updateUserById(id, request);

       return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id) {
        var response = _userService.deleteUserById(id);

        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/photo/upload/{id}")
    public ResponseEntity uploadPhoto(@PathVariable String id, @RequestParam("photo")MultipartFile photo) {
        try {
            _userService.uploadPhoto(id, photo);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

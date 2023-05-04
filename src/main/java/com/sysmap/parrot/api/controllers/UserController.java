package com.sysmap.parrot.api.controllers;

import com.sysmap.parrot.application.requests.User.CreateUser.CreateUserRequest;
import com.sysmap.parrot.application.requests.User.GetUser.GetUserResponse;
import com.sysmap.parrot.application.requests.User.IUserService;
import com.sysmap.parrot.application.requests.User.UpdateUser.UpdateUserRequest;
import com.sysmap.parrot.application.requests.User.UpdateUser.UpdateUserResponse;
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

    @PostMapping()
    public ResponseEntity<String> createUser(@RequestBody CreateUserRequest request) {
        var response = _userService.createUser(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping()
    public ResponseEntity<GetUserResponse> getUser(String id) {
        var response = _userService.getUserById(id);

        return ResponseEntity.ok().body(response);
    }

    @PutMapping()
    public ResponseEntity<UpdateUserResponse> updateUser(String id, @RequestBody UpdateUserRequest request) {
       var response = _userService.updateUserById(id, request);

       return ResponseEntity.ok().body(response);
    }

    @DeleteMapping()
    public ResponseEntity<String> deleteUser(String id) {
        var response = _userService.deleteUserById(id);

        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/photo/upload")
    public ResponseEntity uploadPhoto(@RequestParam("photo")MultipartFile photo) {
        try {
            _userService.uploadPhoto(photo);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

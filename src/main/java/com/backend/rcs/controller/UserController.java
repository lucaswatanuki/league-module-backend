package com.backend.rcs.controller;

import com.backend.rcs.controller.request.UserRequest;
import com.backend.rcs.controller.response.UserResponse;
import com.backend.rcs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("")
    public UserResponse post(@RequestBody UserRequest request){
        return userService.save(request);
    }

    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable("id") String id){
        return userService.findById(id);
    }

    @GetMapping("/all")
    public List<UserResponse> getAll(){
        return userService.findAllUsers();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") String id){
        userService.delete(id);
    }
}

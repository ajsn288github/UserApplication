package com.pnc.training.userapp.controller;

import com.pnc.training.userapp.model.User;
import com.pnc.training.userapp.model.dto.UserDto;
import com.pnc.training.userapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * Created by PL64640 on 8/11/2017.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public UserDto getAllUsers() {

        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public UserDto getUser(@PathVariable("userId") int userId) {

        return userService.getUser(userId);
    }

    @PostMapping
    public UserDto addUser(@Valid @RequestBody User user){

        return userService.addUser(user);
    }

    @PutMapping("/{userId}")
    public UserDto updateUser(@PathVariable("userId") int userId, @Valid @RequestBody User user){

        user.setUserId(userId);
        return userService.updateUser(user);
    }

    @DeleteMapping("/{userId}")
    public UserDto deleteUser(@PathVariable("userId") int userId){

        return userService.deleteUser(userId);
    }


    @GetMapping("/name/{userName}")
    public UserDto getUserByName(@PathVariable("userName") String userName) {

        return userService.getUserByName(userName);
    }

    @GetMapping("/role/{role}")
    public UserDto getUserByRole(@PathVariable("role") String role) {

        return userService.getUserByRole(role);
    }

    @GetMapping("/location/{location}")
    public UserDto getUserByLocation(@PathVariable("location") String location) {

        return userService.getUserByLocation(location);
    }
}

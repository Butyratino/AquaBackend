package com.sergio.bdas2.backend.controller;


import com.sergio.bdas2.backend.model.dto.ChangeRoleRequest;
import com.sergio.bdas2.backend.model.dto.UserDetailsDto;
import com.sergio.bdas2.backend.model.dto.UserDto;
import com.sergio.bdas2.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profile")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(value = "/{username}")
    public ResponseEntity<UserDetailsDto> getUserDetails(@PathVariable("username") String username){
        return userService.getUserDetailsByUsername(username);
    }
    @GetMapping(value = "/all")
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping(value = "/update/{id}")
    public void updateUserDetails(@PathVariable(value = "id") Integer id, @RequestBody UserDetailsDto userDetails){
        userService.updateUserDetails(id, userDetails);
    }

    @PostMapping(value = "/add/{id}")
    public void addUserDetails(@PathVariable(value = "id") Integer id, @RequestBody UserDetailsDto userDetails){
        userService.addUserDetails(id, userDetails);
    }
    @PostMapping(value = "/img/{id}")
    public void updateUserPicture(@PathVariable("id") Integer id, String picture){
        userService.updateUserPicture(id, picture);
    }

    @PostMapping(value = "/admin/changeRole")
    public List<UserDto> changeUserRole(@RequestBody ChangeRoleRequest request){
        return userService.changeUserRole(request);
    }

}

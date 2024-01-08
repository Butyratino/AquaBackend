package com.sergio.bdas2.backend.controller;


import com.sergio.bdas2.backend.model.dto.ChangeRoleRequest;
import com.sergio.bdas2.backend.model.dto.UserDetailsDto;
import com.sergio.bdas2.backend.model.dto.UserDto;
import com.sergio.bdas2.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public List<UserDetailsDto> getAllUsers() {
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
//    @PostMapping(value = "/img/{id}")
//    public void updateUserPicture(@PathVariable("id") Integer id, String picture){
//        userService.updateUserPicture(id, picture);
//    }

    @PostMapping(value = "/img/{id}")
    public ResponseEntity<String> updateUserPicture(@PathVariable("id") Integer id, @RequestBody String base64Image) {
        try {
            // Decode the base64 image string to byte[]
            byte[] imageBytes = java.util.Base64.getDecoder().decode(base64Image);

            // Update user picture in the database
            userService.updateUserPicture(id, imageBytes);

            return new ResponseEntity<>("User picture updated successfully", HttpStatus.OK);
        } catch (Exception e) {
            // Handle exceptions (e.g., invalid base64 format, database errors)
            return new ResponseEntity<>("Failed to update user picture: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/users/{id}/avatar")
    public ResponseEntity<String> getUserAvatar(@PathVariable("id") Integer userId) {
        try {
            // Retrieve the user avatar URL from the database or storage
            String avatarUrl = userService.getUserAvatarUrl(userId);
            return new ResponseEntity<>(avatarUrl, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error fetching avatar: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


//    @PostMapping("/img/{id}")
//    public ResponseEntity<String> updateUserPicture(
//            @PathVariable("id") Long userId,
//            @RequestPart("picture") MultipartFile picture) {
//
//        try {
//            // Save the image data to the BinaryContentTable
//            binaryContentService.saveImage(userId, picture);
//
//            return new ResponseEntity<>("Image uploaded successfully", HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>("Failed to upload image: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @PostMapping(value = "/admin/changeRole")
    public List<UserDetailsDto> changeUserRole(@RequestBody ChangeRoleRequest request){
        return userService.changeUserRole(request);
    }

//    @GetMapping(value = "/get/{userId}")
//    public ResponseEntity<UserDetailsDto> getUserDetailsById(@PathVariable("userId") Integer userId) {
//        return userService.getUserDetailsById(userId);
//    }

}

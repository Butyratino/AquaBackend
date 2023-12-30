package com.sergio.bdas2.backend.service;

import com.sergio.bdas2.backend.model.dto.ChangeRoleRequest;
import com.sergio.bdas2.backend.model.dto.RegistrationUserRequest;
import com.sergio.bdas2.backend.model.dto.UserDetailsDto;
import com.sergio.bdas2.backend.model.dto.UserDto;
import com.sergio.bdas2.backend.model.entity.User;
import com.sergio.bdas2.backend.repository.UserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDao userDao;
    private final BCryptPasswordEncoder passwordEncoder;


    public void register(RegistrationUserRequest user) {
        if(userDao.checkIsUserExistByUsername(user.getUsername())){
            throw new RuntimeException("Username already exists!");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.addNewUser(user);
    }

    public UserDto getUserById(Integer userId) {
        return userDao.getUserById(userId).toUserDto();
    }

    public List<UserDto> getAllUsers() {
        return userDao.getAllUsers();
    }

    public User getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }

    public ResponseEntity<UserDetailsDto> getUserDetailsByUsername(String username) {
        return userDao.getUserDetailsByUsername(username);
    }

    public void updateUserPicture(Integer id, String picture) {
        userDao.updateUserPicture(id, picture);
    }

    public void updateUserDetails(Integer id, UserDetailsDto userDetails) {
        userDao.updateUserDetails(id, userDetails);
    }
    public void addUserDetails(Integer id, UserDetailsDto userDetails) {
        userDao.addUserDetails(id, userDetails);
    }


    public List<UserDto> changeUserRole(ChangeRoleRequest request) {
        return userDao.changeUserRole(request);
    }
}

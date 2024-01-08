package com.sergio.bdas2.backend.repository;

import com.sergio.bdas2.backend.model.dto.ChangeRoleRequest;
import com.sergio.bdas2.backend.model.dto.RegistrationUserRequest;
import com.sergio.bdas2.backend.model.dto.UserDetailsDto;
import com.sergio.bdas2.backend.model.dto.UserDto;
import com.sergio.bdas2.backend.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserDao {


    private static Integer userId = 1;

    private final JdbcTemplate jdbcTemplate;

    public User getUserById(Integer id) {
        String query = "SELECT * FROM USERS WHERE USERID = ?";
        List<User> foundUsers = jdbcTemplate.query(query, new Object[]{id}, User.getUserMapper());
        if (foundUsers.size() != 1) {
            throw new DaoException("User with ID " + id + " not found");
        }
        return foundUsers.get(0);
    }

    public List<UserDetailsDto> getAllUsers() {
        String query = "SELECT * FROM USERS ORDER BY USERID";
        List<UserDetailsDto> foundUsers = jdbcTemplate.query(query, UserDetailsDto.getUserDetailsDtoMapper());
        return foundUsers;
    }

    public void addNewUser(RegistrationUserRequest user) {
        String query = "INSERT INTO USERS (USERID, USERNAME, PASSWORD, ROLE) VALUES (?,?,?,?)";
        jdbcTemplate.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, userId++);
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getRole());
            return ps;
        });
    }

    public User getUserByUsername(String username) {
        String query = "SELECT * FROM USERS WHERE USERNAME like ?";
        List<User> foundUsers = jdbcTemplate.query(query, new Object[]{username}, User.getUserMapper());
        if (foundUsers.size() != 1) {
            throw new RuntimeException("User with username " + username + " not found");
        }
        return foundUsers.get(0);
    }


    public void updateUserDetails(Integer id, UserDetailsDto userDetails) {
        String procedureCall = "{CALL update_user_details (?, ?, ?, ?, ?, ?, ?, ?, ?)}";

        jdbcTemplate.update(
                procedureCall,
                id,
                userDetails.getAvatar(),
                userDetails.getPassword(),
                userDetails.getAddressId(),
                userDetails.getUsername(),
                userDetails.getPhone(),
                userDetails.getRole(),
                userDetails.getHasAvatar(),
                userDetails.getEmail()
        );
    }


    public ResponseEntity<UserDetailsDto> getUserDetailsByUsername(String username) {
        String query = "SELECT * FROM USERS WHERE USERNAME like ?"; //todo
        List<UserDetailsDto> foundUsers = jdbcTemplate.query(query, new Object[]{username}, UserDetailsDto.getUserDetailsDtoMapper());
        if (foundUsers.size() != 1) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(foundUsers.get(0), HttpStatus.OK);
    }

    public void addUserDetails(Integer id, UserDetailsDto userDetails) { //todo
        String query = "insert into contact_details(name, surname, email, city, phone_number, document_number, id_user, img)" +
                "values(?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, userDetails.getUsername());
            ps.setString(2, userDetails.getUsername());
            ps.setString(3, userDetails.getEmail());
            ps.setString(4, userDetails.getUsername());
            ps.setString(5, userDetails.getUsername());
            ps.setString(6, userDetails.getUsername());
            ps.setInt(7, id);
            ps.setString(8,userDetails.getUsername());
            return ps;
        });
    }

    public List<UserDetailsDto> changeUserRole(ChangeRoleRequest request) {
        String query = "UPDATE USERS SET ROLE = ? WHERE USERID = ?";
        jdbcTemplate.update(query, request.getRole().equals("admin") ? "admin" : "user", request.getIdUser());
        return getAllUsers();
    }

    public boolean checkIsUserExistByUsername(String username) {
        String query = "SELECT * FROM USERS WHERE USERNAME like ?";
        List<User> foundUsers = jdbcTemplate.query(query, new Object[]{username}, User.getUserMapper());
        return foundUsers.size() > 0;
    }



    public void updateUserPicture(Integer id, byte[] picture) {
        String query = "UPDATE USERS SET AVATAR = ? WHERE USERID = ?";
        jdbcTemplate.update(query, picture, id);
    }


    public String getUserAvatarUrl(Integer userId) {
        System.out.println("DAO getUserAvatarUrl was cococolled!");
        String query = "SELECT AVATAR FROM USERS WHERE USERID = ?";
        List<byte[]> avatarBytesList = jdbcTemplate.query(query, new Object[]{userId}, (rs, rowNum) -> rs.getBytes("AVATAR"));

        if (!avatarBytesList.isEmpty()) {
            // Convert the byte array to a Base64-encoded string if needed
            return Base64.getEncoder().encodeToString(avatarBytesList.get(0));
        } else {
            // Handle case when user is not found or avatar is null
            return null; // Or throw an exception if you prefer
        }
    }

    public byte[] getUserAvatarData(Integer userId) {
        System.out.println("DAO getUserAvatarData was called!");
        String query = "SELECT AVATAR FROM USERS WHERE USERID = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{userId}, byte[].class);
    }

}

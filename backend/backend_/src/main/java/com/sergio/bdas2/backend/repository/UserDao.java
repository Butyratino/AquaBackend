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
import java.util.List;

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

    public User getUserByUsername(String username) {
        String query = "SELECT * FROM USERS WHERE USERNAME like ?";
        List<User> foundUsers = jdbcTemplate.query(query, new Object[]{username}, User.getUserMapper());
        if (foundUsers.size() != 1) {
            throw new RuntimeException("User with username " + username + " not found");
        }
        return foundUsers.get(0);
    }

    public List<UserDto> getAllUsers() {
        String query = "SELECT * FROM USER_DETAILS_VIEW ORDER BY ID_USER"; //todo view
        List<UserDto> foundUsers = jdbcTemplate.query(query, UserDto.getUserDtoMapper());
        return foundUsers;
    }
    public void updateUserDetails(Integer id, UserDetailsDto userDetails) {
        String query = "UPDATE CONTACT_DETAILS SET NAME = ?, SURNAME = ?, EMAIL = ?, CITY = ?," + //todo create userdetail table
                " PHONE_NUMBER = ?, DOCUMENT_NUMBER = ?, IMG = ? WHERE ID_USER = ?";
        jdbcTemplate.update(query, userDetails.getName(), userDetails.getSurname(), userDetails.getEmail(),
                userDetails.getCity(), userDetails.getPhoneNumber(), userDetails.getDocumentNumber(), userDetails.getImage(), id);
    }

    public ResponseEntity<UserDetailsDto> getUserDetailsByUsername(String username) {
        String query = "SELECT * FROM USER_DETAILS_VIEW WHERE LOGIN like ?"; //todo
        List<UserDetailsDto> foundUsers = jdbcTemplate.query(query, new Object[]{username}, UserDetailsDto.getMapper());
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
            ps.setString(1, userDetails.getName());
            ps.setString(2, userDetails.getSurname());
            ps.setString(3, userDetails.getEmail());
            ps.setString(4, userDetails.getCity());
            ps.setString(5, userDetails.getPhoneNumber());
            ps.setString(6, userDetails.getDocumentNumber());
            ps.setInt(7, id);
            ps.setString(8,userDetails.getImage());
            return ps;
        });
    }

    public List<UserDto> changeUserRole(ChangeRoleRequest request) {
        String query = "UPDATE USERS SET ROLE = ? WHERE USERID = ?";
        jdbcTemplate.update(query, request.getRole().equals("admin") ? "admin" : "user", request.getIdUser());//todo
        return getAllUsers();
    }

    public boolean checkIsUserExistByUsername(String username) {
        String query = "SELECT * FROM USERS WHERE USERNAME like ?";
        List<User> foundUsers = jdbcTemplate.query(query, new Object[]{username}, User.getUserMapper());
        return foundUsers.size() > 0;
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

    public void updateUserPicture(Integer id, String picture) {
        String query = "UPDATE CONTACT_DETAILS SET IMG = ? WHERE USERID = ?";
        jdbcTemplate.update(query, picture, id);
    }

}

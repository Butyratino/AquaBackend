package com.sergio.bdas2.backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.jdbc.core.RowMapper;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDetailsDto {
    private Integer userId;
    private String username;
    private String role;
    private byte[] avatar;
    private String password;
    private Integer addressId;
    private String phone;
    private Integer hasAvatar;
    private String email;

    public UserDetailsDto(String username){
        this.username = username;
    }

    public static RowMapper<UserDetailsDto> getUserDetailsDtoMapper() {
        return (rs, rowNum) -> {
            UserDetailsDto user = new UserDetailsDto();
            user.setUserId(rs.getInt("USERID"));
            user.setRole(rs.getString("ROLE"));
            user.setUsername(rs.getString("USERNAME"));
            user.setAvatar(rs.getBytes("AVATAR"));
            user.setPassword(rs.getString("PASSWORD"));
            user.setAddressId(rs.getInt("ADDRESSID"));
            user.setPhone(rs.getString("PHONE"));
            user.setHasAvatar(rs.getInt("HASAVATAR"));
            user.setEmail(rs.getString("EMAIL"));
            return user;
        };
    }
}

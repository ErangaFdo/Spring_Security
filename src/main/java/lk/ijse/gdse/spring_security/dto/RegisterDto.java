package lk.ijse.gdse.spring_security.dto;

import lk.ijse.gdse.spring_security.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class RegisterDto {
    private String userName;
    private String userPassword;
    private Role role;
}

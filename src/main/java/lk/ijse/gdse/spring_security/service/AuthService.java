package lk.ijse.gdse.spring_security.service;

import lk.ijse.gdse.spring_security.dto.AuthDto;
import lk.ijse.gdse.spring_security.dto.AuthResponseDto;
import lk.ijse.gdse.spring_security.dto.RegisterDto;
import lk.ijse.gdse.spring_security.entity.Role;
import lk.ijse.gdse.spring_security.entity.User;
import lk.ijse.gdse.spring_security.repository.UserRepository;
import lk.ijse.gdse.spring_security.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthResponseDto authenticate(AuthDto authDto) {
        User user = userRepository.findByUsername(authDto.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        if (passwordEncoder.matches(authDto.getPassword(),user.getUserName())){
            throw new BadCredentialsException("Invalid Password");
        }

        String token = jwtUtil.generateToken(authDto.getUsername());
        return new AuthResponseDto(token);
    }

    public String register(RegisterDto registerDTO) {
        if (userRepository.findByUsername(registerDTO.getUserName()).isPresent()) {
            throw new RuntimeException("Username is already exist");
        }
        User user = User.builder()
                .userName(registerDTO.getUserName())
                .userPassword((passwordEncoder.encode(registerDTO.getUserPassword()))
                .role(Role.valueOf(registerDTO.getRole()))
                .build();
        userRepository.save(user);
        return "User registered successfully";
    }

}

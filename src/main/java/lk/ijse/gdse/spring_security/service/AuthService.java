package lk.ijse.gdse.spring_security.service;

import lk.ijse.gdse.spring_security.dto.AuthDto;
import lk.ijse.gdse.spring_security.dto.AuthResponseDto;
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

}

package lk.ijse.gdse.spring_security.controller;

import lk.ijse.gdse.spring_security.dto.ApiResponse;
import lk.ijse.gdse.spring_security.dto.AuthDto;
import lk.ijse.gdse.spring_security.dto.RegisterDto;
import lk.ijse.gdse.spring_security.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> registerUser(@RequestBody RegisterDto registerDTO) {
        return ResponseEntity.ok(
                new ApiResponse(
                        200,
                        "User Register Successfully",
                        authService.register(registerDTO)
                )
        );
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody AuthDto authDTO) {
        return ResponseEntity.ok(
                new ApiResponse(
                        200,
                        "OK",
                        authService.authenticate(authDTO)
                )
        );
    }
}
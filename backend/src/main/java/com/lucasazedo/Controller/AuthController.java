package com.lucasazedo.Controller;

import com.lucasazedo.DTO.UserSignInRequestDTO;
import com.lucasazedo.DTO.UserSignResponseDTO;
import com.lucasazedo.DTO.UserSignUpRequestDTO;
import com.lucasazedo.Service.AuthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    AuthService authService;

    @PostMapping("/signin")
    public ResponseEntity<UserSignResponseDTO> signIn(@RequestBody @Valid UserSignInRequestDTO request){
        UserSignResponseDTO response = authService.signIn(request);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/signup")
    public ResponseEntity<UserSignResponseDTO> signUp(@RequestBody @Valid UserSignUpRequestDTO request){
        UserSignResponseDTO response = authService.signUp(request);

        return ResponseEntity.ok(response);
    }
}

package com.lucasazedo.Controller;

import com.lucasazedo.DTO.UserSignInRequestDTO;
import com.lucasazedo.DTO.UserSignResponseDTO;
import com.lucasazedo.Service.AuthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    AuthService authService;

    @PostMapping
    public ResponseEntity<UserSignResponseDTO> signIn(@RequestBody @Valid UserSignInRequestDTO request){
        UserSignResponseDTO response = authService.signIn(request);

        return ResponseEntity.ok(response);
    }
}

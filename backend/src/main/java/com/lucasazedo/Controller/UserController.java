package com.lucasazedo.Controller;

import com.lucasazedo.DTO.UserResponseDTO;
import com.lucasazedo.DTO.UserSignUpRequestDTO;
import com.lucasazedo.Service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody @Valid UserSignUpRequestDTO dto){
        UserResponseDTO response = userService.createUser(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable UUID id){
        userService.deleteUser(id);
    }
}

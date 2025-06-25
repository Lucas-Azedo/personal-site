package com.lucasazedo.Service;

import com.lucasazedo.DTO.UserSignInRequestDTO;
import com.lucasazedo.DTO.UserSignResponseDTO;
import com.lucasazedo.Exception.Business.InvalidCredentialsException;
import com.lucasazedo.Exception.Business.UserNotFoundException;
import com.lucasazedo.Model.User;
import com.lucasazedo.Repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    public UserSignResponseDTO signIn(UserSignInRequestDTO request){
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException("Email não cadastrado!"));

        if(!passwordEncoder.matches(user.getPassword(), request.getPassword())){
            throw new InvalidCredentialsException("Senha incorreta!");
        }

        

        return new UserSignResponseDTO(user.getName());
    }
}

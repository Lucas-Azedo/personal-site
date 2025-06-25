package com.lucasazedo.Service;

import com.lucasazedo.DTO.UserSignInRequestDTO;
import com.lucasazedo.DTO.UserSignResponseDTO;
import com.lucasazedo.DTO.UserSignUpRequestDTO;
import com.lucasazedo.Exception.Business.InvalidCredentialsException;
import com.lucasazedo.Exception.Business.UserNotFoundException;
import com.lucasazedo.Model.User;
import com.lucasazedo.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    UserService userService;

    public UserSignResponseDTO signIn(UserSignInRequestDTO request){
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException("Email não cadastrado!"));

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new InvalidCredentialsException("Senha incorreta!");
        }

        return new UserSignResponseDTO(user.getName());
    }

    public UserSignResponseDTO signUp(UserSignUpRequestDTO request){
        userService.createUser(request);

        UserSignInRequestDTO sign = new UserSignInRequestDTO(
          request.getEmail(),
          request.getPassword()
        );

        return signIn(sign);
    }
}

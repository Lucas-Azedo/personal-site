package com.lucasazedo.Service;

import com.lucasazedo.DTO.UserResponseDTO;
import com.lucasazedo.DTO.UserSignUpRequestDTO;
import com.lucasazedo.Exception.Business.InvalidInput;
import com.lucasazedo.Model.User;
import com.lucasazedo.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserResponseDTO createUser(UserSignUpRequestDTO dto){
        boolean hasCpf = dto.getCpf() != null && !dto.getCpf().isBlank();
        boolean hasCnpj = dto.getCnpj() != null && !dto.getCnpj().isBlank();

        if (hasCpf && hasCnpj) {
            throw new InvalidInput("Informe apenas um: CPF ou CNPJ, nunca ambos.");
        }
        if (!hasCpf && !hasCnpj) {
            throw new InvalidInput("Informe pelo menos um: CPF ou CNPJ.");
        }

        User user = new User();

        user.setId(UUID.randomUUID());
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setCnpj(dto.getCnpj());
        user.setCpf(dto.getCpf());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));


        userRepository.save(user);

        return new UserResponseDTO(user.getId(), user.getName(), user.getEmail());
    }
}

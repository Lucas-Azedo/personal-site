package com.lucasazedo.Service;

import com.lucasazedo.DTO.UserResponseDTO;
import com.lucasazedo.DTO.UserSignUpRequestDTO;
import com.lucasazedo.Exception.Business.InvalidInputException;
import com.lucasazedo.Exception.Business.UserNotFoundException;
import com.lucasazedo.Model.User;
import com.lucasazedo.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserResponseDTO createUser(UserSignUpRequestDTO dto){
        validateCpfCnpj(dto);
        validateDuplicates(dto);

        User user = new User();

        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setCnpj(dto.getCnpj());
        user.setCpf(dto.getCpf());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        userRepository.save(user);

        return new UserResponseDTO(user.getId(), user.getName(), user.getEmail());
    }


    @Transactional(readOnly = true)
    public UserResponseDTO getUserById(UUID id){
        User user = getUserEntityById(id);

        return new UserResponseDTO(user.getId(), user.getName(), user.getEmail());
    }

    @Transactional(readOnly = true)
    public List<UserResponseDTO> getAllUsers(){
        return userRepository.findAll().stream()
                .map(user -> new UserResponseDTO(user.getId(), user.getName(), user.getEmail()))
                .collect(Collectors.toList());
    }

    public void deleteUser(UUID id){
        User user = getUserEntityById(id);

        userRepository.delete(user);
    }

    //Utils
    @Transactional(readOnly = true)
    private User getUserEntityById(UUID id){
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Usuário nao encontrado"));
    }

    private void validateCpfCnpj(UserSignUpRequestDTO dto) {
        boolean hasCpf = dto.getCpf() != null && !dto.getCpf().isBlank();
        boolean hasCnpj = dto.getCnpj() != null && !dto.getCnpj().isBlank();

        if (hasCpf && hasCnpj) {
            throw new InvalidInputException("Informe apenas um: CPF ou CNPJ, nunca ambos.");
        }
        if (!hasCpf && !hasCnpj) {
            throw new InvalidInputException("Informe pelo menos um: CPF ou CNPJ.");
        }
    }

    private void validateDuplicates(UserSignUpRequestDTO dto) {
        if (dto.getCpf() != null && userRepository.existsByCpf(dto.getCpf())) {
            throw new InvalidInputException("CPF já cadastrado.");
        }

        if (dto.getCnpj() != null && userRepository.existsByCnpj(dto.getCnpj())) {
            throw new InvalidInputException("CNPJ já cadastrado.");
        }

        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new InvalidInputException("Email já cadastrado.");
        }
    }
}

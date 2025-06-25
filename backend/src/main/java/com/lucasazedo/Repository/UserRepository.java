package com.lucasazedo.Repository;

import com.lucasazedo.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    boolean existsByCpf(String cpf);
    boolean existsByCnpj(String cnpj);
    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);
}

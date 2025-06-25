package com.lucasazedo.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy =  GenerationType.UUID)
    UUID id;

    @Column(unique = true)
    String email;

    @Column(unique = true)
    String cpf;

    @Column(unique = true)
    String cnpj;

    String name;
    String password;

}

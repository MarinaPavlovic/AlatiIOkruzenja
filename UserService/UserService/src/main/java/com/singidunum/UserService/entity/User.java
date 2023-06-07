package com.singidunum.UserService.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Entity
@Table(name="users")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "full_name")
    @NonNull
    private String fullName;
    @Column(name = "username")
    @NonNull
    private String username;
    @Column(name = "password")
    @NonNull
    private String password;
    @Column(name = "email")
    @NonNull
    @Email
    private String email;

}

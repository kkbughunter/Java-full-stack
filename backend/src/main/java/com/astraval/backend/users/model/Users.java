package com.astraval.backend.users.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue()
    @Column(name = "user_id", length = 100)
    private Long userId;

    @Column(name = "user_name", length = 100)
    private String userName;
    
    @Column(name = "user_email", length = 100)
    private String userEmail;

    @Column(name = "user_password", length = 100)
    private String userPassword;
}

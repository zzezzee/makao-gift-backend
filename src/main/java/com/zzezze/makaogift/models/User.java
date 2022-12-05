package com.zzezze.makaogift.models;

import com.zzezze.makaogift.dtos.UserDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String username;

    private String encodedPassword;

    private Long amount;

    public User() {
    }


    public User(Long id, String name, String username, String encodedPassword, Long amount) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.encodedPassword = encodedPassword;
        this.amount = amount;
    }

    public String username() {
        return username;
    }

    public Long amount() {
        return amount;
    }

    public boolean authenticate(String password, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(password, encodedPassword);
    }

    public void changePassword(String password, PasswordEncoder passwordEncoder) {
        encodedPassword = passwordEncoder.encode(password);
    }

    public static User fake(String username) {
        return new User(1L, "zzezze", username, "encodedPassword", 50000L);
    }

    public UserDto toDto() {
        return new UserDto(amount);
    }

    public void order(Long totalPrice) {
        amount -= totalPrice;
    }
}

package com.example.fetelix.models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tbl_users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 100, nullable = false)
    private String family_name;
    @Column(length = 100, nullable = false)
    private String given_name;
    @Column(length = 100, nullable = false)
    private String locale;
    @Column(nullable = false)
    private String picture;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String email;
    @Column(length = 20, nullable = false)
    private String phone;
    @Column(length = 200, nullable = false)
    private String password;
}
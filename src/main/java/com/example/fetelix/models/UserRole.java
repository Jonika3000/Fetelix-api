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
@Table(name="tbl_user_roles")
@IdClass(UserRolePK.class)

public class UserRole {
    @Id
    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;
    @Id
    @ManyToOne
    @JoinColumn(name="role_id", nullable = false)
    private Role role;
}
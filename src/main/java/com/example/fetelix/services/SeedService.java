package com.example.fetelix.services;

import com.example.fetelix.constants.Roles;
import com.example.fetelix.models.Role;
import com.example.fetelix.models.User;
import com.example.fetelix.models.UserRole;
import com.example.fetelix.repositories.RoleRepository;
import com.example.fetelix.repositories.UserRepository;
import com.example.fetelix.repositories.UserRoleRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Data
@AllArgsConstructor
public class SeedService {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;

    public void seedRoleData() {
        if(roleRepository.count()==0) {
            var admin = Role
                    .builder()
                    .name(Roles.Admin)
                    .build();
            roleRepository.save(admin);

            var user = Role
                    .builder()
                    .name(Roles.User)
                    .build();
        }
    }
    public void seedUserData() {
//        if(userRepository.count() == 0) {
//            var user = User
//                    .builder()
//                    .name("admin")
//                    .lastName("admin")
//                    .phone("admin")
//                    .email("admin@gmail.com")
//                    .password(passwordEncoder.encode("123456"))
//                    .build();
//            userRepository.save(user);
//            var role = roleRepository.findByName(Roles.Admin);
//            var ur = UserRole
//                    .builder()
//                    .role(role)
//                    .user(user)
//                    .build();
//            userRoleRepository.save(ur);
//        }
    }
}

package com.estc.mediatech.config;

import com.estc.mediatech.Repositories.ClientDao;
import com.estc.mediatech.Repositories.UserDao;
import com.estc.mediatech.models.ClientEntity;
import com.estc.mediatech.models.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserDao userDao;

    @Autowired
    private ClientDao clientDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // 1. Create ADMIN if not exists
        if (userDao.findByUsername("admin").isEmpty()) {
            UserEntity admin = new UserEntity();
            admin.setUsername("admin");
            // Although we use NoOp, it's good practice to use the encoder bean
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setRole("ADMIN");
            userDao.save(admin);
            System.out.println("Created ADMIN user (admin/admin)");
        }

        // 2. Create USER if not exists
        UserEntity user = userDao.findByUsername("user").orElse(null);
        if (user == null) {
            user = new UserEntity();
            user.setUsername("user");
            user.setPassword(passwordEncoder.encode("1234"));
            user.setRole("USER");
            user = userDao.save(user);
            System.out.println("Created USER user (user/1234)");
        }

        // 3. Ensure User has a Client Profile
        try {
            if (clientDao.findByClientUser_Username("user").isEmpty()) {
                System.out.println("DEBUG: Client profile missing for 'user', creating one...");
                ClientEntity client = new ClientEntity();
                client.setNom("User");
                client.setPrenom("Test");
                client.setTelephone("1234567890");
                client.setClientUser(user);
                clientDao.save(client);
                System.out.println("DEBUG: Linked Client Profile to USER successfully.");
            } else {
                System.out.println("DEBUG: Client profile already exists for 'user'.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

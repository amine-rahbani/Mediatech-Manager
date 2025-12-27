package com.estc.mediatech;

import com.estc.mediatech.Repositories.UserDao;
import com.estc.mediatech.models.UserEntity;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MediatechApplication {

    public static void main(String[] args) {
        SpringApplication.run(MediatechApplication.class, args);
    }

    @Bean
    CommandLineRunner run(UserDao userDao) {
        return args -> {
            // 1. Create ADMIN if not exists
            if (userDao.findByUsername("admin").isEmpty()) {
                UserEntity admin = new UserEntity();
                admin.setUsername("admin");
                admin.setPassword("1234");
                admin.setRole("ADMIN");
                userDao.save(admin);
                System.out.println("ADMIN USER CREATED: admin / 1234");
            }

            // 2. Create NORMAL USER if not exists (THIS IS NEW)
            if (userDao.findByUsername("user").isEmpty()) {
                UserEntity user = new UserEntity();
                user.setUsername("user");
                user.setPassword("1234");
                user.setRole("USER");
                userDao.save(user);
                System.out.println("NORMAL USER CREATED: user / 1234");
            }
        };
    }
}
package com.example.authorbook.service;


import com.example.authorbook.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface UserService {

    void save(User user, MultipartFile multipartFile);

    Optional<User> findByEmail(String email);
}

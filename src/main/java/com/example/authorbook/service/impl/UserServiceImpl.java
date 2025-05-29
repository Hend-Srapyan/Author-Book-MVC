package com.example.authorbook.service.impl;

import com.example.authorbook.entity.User;
import com.example.authorbook.repository.UserRepository;
import com.example.authorbook.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Value("${author.book.upload.path}")
    private String uploadPath;

    private final UserRepository userRepository;

    @SneakyThrows
    @Override
    public void save(User user, MultipartFile multipartFile) {
        String fileName;
        if(!multipartFile.isEmpty()){
            fileName = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
            File file = new File(uploadPath, fileName);
            multipartFile.transferTo(file);
            user.setImageName(fileName);
        }
        userRepository.save(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}

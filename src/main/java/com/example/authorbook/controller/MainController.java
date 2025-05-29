package com.example.authorbook.controller;

import com.example.authorbook.security.CurrentUser;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class MainController {

    @Value("${author.book.upload.path}")
    private String uploadPath;

    @GetMapping("/")
    public String mainPage(ModelMap modelMap, @AuthenticationPrincipal CurrentUser currentUser) {
       if (currentUser != null) {
           modelMap.put("user", currentUser.getUser());
       }
        return "index";
    }


    @GetMapping(value = "/getImage", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImage(@RequestParam("imageName") String imgName) {
        File file = new File(uploadPath + imgName);
        if (file.exists()) {
            try (InputStream is = new FileInputStream(file)) {
                return IOUtils.toByteArray(is);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}

package com.example.authorbook.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;

@Controller
public class MainController {

    @Value("${author.book.upload.path}")
    private String uploadPath;

    @GetMapping("/")
    public String mainPage() {
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

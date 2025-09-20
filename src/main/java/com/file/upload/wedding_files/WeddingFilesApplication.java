package com.file.upload.wedding_files;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WeddingFilesApplication {

    public static void main(String[] args) {
        System.setProperty("GOOGLE_APPLICATION_CREDENTIALS", "src/main/resources/aplication_creds.json");
        SpringApplication.run(WeddingFilesApplication.class, args);
    }

}

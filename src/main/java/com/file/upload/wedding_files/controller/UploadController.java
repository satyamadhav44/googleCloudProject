package com.file.upload.wedding_files.controller;

import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@CrossOrigin(origins = {"https://234044445-atari-embeds.googleusercontent.com", "*"})
@RequestMapping("/api/upload")
public class UploadController {

    private static final String BUCKET_NAME = "wedding_photos_satya";
    private final Storage storage;

    public UploadController(Storage storage) {
        this.storage = storage;
    }

    @PostMapping
    public ResponseEntity<String> uploadImages(@RequestParam("files") MultipartFile[] files) {
        if (files.length == 0) {
            return new ResponseEntity<>("No files selected.", HttpStatus.BAD_REQUEST);
        }

        String folderName = "wedding_photos"; // Define your folder name here

        for (MultipartFile file : files) {
            try {
                // Generate a unique file name to avoid collisions
                String uniqueFileName = UUID.randomUUID() + "-" + file.getOriginalFilename();
                String objectName = String.format("%s/%s", folderName, uniqueFileName);

                BlobId blobId = BlobId.of(BUCKET_NAME, objectName);
                BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(file.getContentType()).build();

                // Upload the file's bytes to Google Cloud Storage
                storage.create(blobInfo, file.getBytes());

            } catch (IOException e) {
                System.err.println("Failed to upload file: " + file.getOriginalFilename() + ". Error: " + e.getMessage());
                // You can choose to return an error here or continue processing other files
            }
        }

        return new ResponseEntity<>("All files processed.", HttpStatus.OK);
    }
}

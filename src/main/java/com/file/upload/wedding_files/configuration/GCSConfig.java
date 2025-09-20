package com.file.upload.wedding_files.configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class GCSConfig {

    @Bean
    public Storage storage() throws IOException {
        // This automatically uses the credentials from the GOOGLE_APPLICATION_CREDENTIALS
        // environment variable.
        return StorageOptions.getDefaultInstance().getService();
    }
}

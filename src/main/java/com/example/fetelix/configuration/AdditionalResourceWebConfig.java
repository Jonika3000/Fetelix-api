package com.example.fetelix.configuration;

import com.example.fetelix.storage.StorageProperties;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@AllArgsConstructor
public class AdditionalResourceWebConfig implements WebMvcConfigurer {
    private final StorageProperties storageProperties;
    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/"+storageProperties.getFolder()+"/**")
                .addResourceLocations("file:"+storageProperties.getFolder()+"\\");
    }
}
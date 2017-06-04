package com.example;

import ee.iglu.skeleton.SkeletonConfig;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(SkeletonConfig.class)
public class ApplicationConfig {
}

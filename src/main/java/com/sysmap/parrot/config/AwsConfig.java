package com.sysmap.parrot.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsConfig {

    @Value("${app-config.api-env}")
    private String apiEnv;

    @Bean
    public AmazonS3 amazonS3() {
        String localstackUrl = "http://localstack_demo:4566";

        if (apiEnv.equals("local")) {
            localstackUrl = "http://s3.localhost.localstack.cloud:4566";
        }

        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials("mykey", "mykey")))
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(localstackUrl, Regions.US_WEST_2.getName()))
                .build();
    }
}

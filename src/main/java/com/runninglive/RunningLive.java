package com.runninglive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

/**
 * User: alarive
 */
@SpringBootApplication
public class RunningLive extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(RunningLive.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(RunningLive.class, args);
    }

}

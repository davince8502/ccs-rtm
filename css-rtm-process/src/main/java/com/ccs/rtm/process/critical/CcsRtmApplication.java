package com.ccs.rtm.process.critical;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class CcsRtmApplication {
    public static void main(String[] args) {
        SpringApplication.run(CcsRtmApplication.class, args);
    }
}

package com.zhuyitech.parking.monitor;

import de.codecentric.boot.admin.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAdminServer
public class SpringbootMonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootMonitorApplication.class, args);
    }
}

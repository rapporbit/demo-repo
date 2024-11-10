package com.rapporbit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication //声明该类是SpringBoot的引导类,ComponentScan并且会自动扫描该类所在包及其子包下的所有类(IOC容器)
public class SpringbootWeb01Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootWeb01Application.class, args);
    }

}

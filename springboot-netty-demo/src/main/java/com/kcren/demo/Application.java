package com.kcren.demo;



import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @author yuzc
 */
@SpringBootApplication
@Slf4j
public class Application {


    /**
     * Start
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        log.info("Start Success");
    }


}

package com.kcren.demo;


import com.zhongfeng.sms.base.common.seq.SnowFlake;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Random;

/**
 * @author yuzc
 */
@SpringBootApplication
@Slf4j
@MapperScan(value = {"com.zhongfeng.sms.persistence.auto.mapper"})
public class Application {

    @Bean
    public SnowFlake getIdWorker() {
        int workerId = new Random().nextInt(20);
        int dataCenterId = new Random().nextInt(20);
        SnowFlake worker = new SnowFlake(workerId, dataCenterId);
        return worker;
    }
    /**
     * Start
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        log.info("Start Success");
    }


}

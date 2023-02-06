package io.github.wuma2020.logpushexample;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@ComponentScan(basePackages = {"io.github.wuma2020"})
@SpringBootApplication
@Slf4j
public class LogPushExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(LogPushExampleApplication.class, args);
        log.info(log.getClass().getName() + " | 测试");
    }

}

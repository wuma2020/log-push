package io.github.wuma2020.logpushexample;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

/**
 * The type Log push example application.
 */
@SpringBootApplication
@Slf4j
public class LogPushExampleApplication {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(LogPushExampleApplication.class, args);
        log.error(log.getClass().getName() + " | 测试");
    }

}

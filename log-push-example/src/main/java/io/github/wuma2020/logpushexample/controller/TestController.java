package io.github.wuma2020.logpushexample.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Copyright (C), 2022-2023, wuma2020
 * Author: wuma2020
 * Date: 2023/1/20 08:54
 * FileName: TestController
 * Description:
 */
@RestController
@Slf4j
public class TestController {


    /**
     * Get string string.
     *
     * @return the string
     */
    @GetMapping("tt")
    public String getString(){

        log.error("  我是报错信息" + log.getClass().getName());
        return "ss";
    }

}

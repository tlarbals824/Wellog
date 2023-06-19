package com.sim.wellog.test;

import com.sim.wellog.log.Wellog;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Wellog
    @GetMapping("/")
    public String test() throws InterruptedException {
        Thread.sleep(1000);
        throw new RuntimeException("test");
//        return "Hello World!";
    }
}

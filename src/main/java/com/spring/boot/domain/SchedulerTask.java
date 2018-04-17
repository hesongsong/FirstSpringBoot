package com.spring.boot.domain;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Description:
 *
 * @JDK Version:1.8.0_40
 * Version: V1.0
 * Author: hs
 * Date: 2018/4/16 15:43
 */
@Component
public class SchedulerTask {
    private int count = 0;

    @Scheduled(cron = "*/6 * * * * ?")
    private void process() {
        System.out.println("this is scheduler task runing  "+(count++));
    }

    @Scheduled(fixedDelay = 10000)
    private void display() {
        System.out.println("================the test ================");
    }
}

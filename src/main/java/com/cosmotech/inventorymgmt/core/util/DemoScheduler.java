package com.cosmotech.inventorymgmt.core.util;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
@Service
public class DemoScheduler {
    @Scheduled(cron = "0 */1 * * * *")
    public void runEveryMinute(){
        System.out.println("Running every minute at:"+ LocalDateTime.now());
    }
}

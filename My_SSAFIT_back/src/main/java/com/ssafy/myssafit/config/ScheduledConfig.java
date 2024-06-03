package com.ssafy.myssafit.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
@EnableScheduling
public class ScheduledConfig {
 
	// thread pool size 지정 -> 현재느 ㄴ최대 10개의 스레드가 동시에 스케줄된 작업 처리 가능
    private final int POOL_SIZE = 10;
 
    /** 
     * @return
     * @author HwangYJ
     * @param scheduler
     * 스케쥴된 작업이 POOL_SIZE를 사용하도록 설정
     * 
     * EnableScheduling : Spring 스케쥴러를 활성화하여 Scheduled annotation 사용 가능
     */
    public TaskScheduler scheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(POOL_SIZE);
        return scheduler;
    }
}
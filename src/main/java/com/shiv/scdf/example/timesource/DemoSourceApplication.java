package com.shiv.scdf.example.timesource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.function.Supplier;

@SpringBootApplication
public class DemoSourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoSourceApplication.class, args);
    }

    @Bean
    public Supplier<Flux<String>> emit() {
        return () ->
            Flux.interval(Duration.ofMillis(1))
                .onBackpressureLatest()
                .map(aLong -> aLong + " - demo")
                .log();
    }
}

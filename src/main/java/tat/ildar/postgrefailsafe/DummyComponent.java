package tat.ildar.postgrefailsafe;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class DummyComponent implements Runnable {

    private final ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

    private final PostgreService postgreService;

    @PostConstruct
    public void postConstruct() {
        scheduledExecutorService.scheduleAtFixedRate(this, 0, 5, TimeUnit.SECONDS);
    }

    public DummyComponent(PostgreService postgreService) {
        this.postgreService = postgreService;
    }

    @Override
    public void run() {
        try {
            log.info("Validating connection..");
            postgreService.validateConnection();
            log.info("Connection is live");
        } catch (Exception e) {
            log.error("Failed to validate connection", e);
        }
    }

    @PreDestroy
    public void destroy() {
        scheduledExecutorService.shutdown();
    }
}

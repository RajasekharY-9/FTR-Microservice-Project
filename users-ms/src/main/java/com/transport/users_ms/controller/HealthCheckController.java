package com.transport.users_ms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    @Autowired
    @Qualifier("diskSpaceHealthIndicator")
    private HealthIndicator healthIndicator;

    @GetMapping("/health")
    public Health checkHealth() {
        return healthIndicator.health();
    }
}

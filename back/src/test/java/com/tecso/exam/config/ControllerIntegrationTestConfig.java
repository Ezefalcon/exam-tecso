package com.tecso.exam.config;


import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.web.server.LocalServerPort;

public class ControllerIntegrationTestConfig extends SimpleIntegrationTestConfiguration {
    @LocalServerPort
    protected int port;

    protected String localUrl = "http://localhost:";

    @BeforeAll
    final void beforeAllTests(){
        this.localUrl = this.localUrl + port;
    }
}

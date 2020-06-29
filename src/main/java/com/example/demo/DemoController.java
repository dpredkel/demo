package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.UUID;

@RestController
public class DemoController {

    private static final UUID uuid = UUID.randomUUID();

    @RequestMapping("/")
    public Response index() {
        return Response.builder()
                .uuid(uuid)
                .time(Instant.now().toEpochMilli())
                .build();
    }
}

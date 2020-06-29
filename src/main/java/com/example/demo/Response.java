package com.example.demo;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class Response {
    UUID uuid;
    long time;
}

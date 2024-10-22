package com.example.graphqldemo.domain.version.entity;

import jakarta.persistence.Entity;
import org.springframework.security.core.parameters.P;

import java.util.Date;

public record Version(String name, Date releaseDate) {
    public static Version getVersion(){
        return new Version("1.0.0", new Date());
    }
}

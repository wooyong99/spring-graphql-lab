package com.example.graphqldemo.domain.version.entity;

import java.util.Date;

public record Version(String name, String description ,Date releaseDate) {
    public static Version getVersion(){
        return new Version("1.0.0", "This is New Version", new Date());
    }
}

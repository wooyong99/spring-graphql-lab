package com.example.graphqldemo.domain.version.controller;

import com.example.graphqldemo.domain.version.entity.Version;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class VersionController {

    @QueryMapping
    public Version getVersion(){
        return Version.getVersion();
    }
}

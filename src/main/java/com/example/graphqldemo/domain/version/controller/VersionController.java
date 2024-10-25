package com.example.graphqldemo.domain.version.controller;

import com.example.graphqldemo.domain.version.entity.Version;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class VersionController {

    @QueryMapping
    public Version skipDirectiveTest(@Argument Boolean isShow){ return Version.getVersion();}

    @QueryMapping
    public Version includeDirectiveTest(@Argument Boolean isShow) { return Version.getVersion();}

}
